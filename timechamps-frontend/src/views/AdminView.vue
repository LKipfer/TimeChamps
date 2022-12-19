<script setup lang="ts">
import MainTitle from "@/components/MainTitle.vue";
import { onMounted, type Ref, ref } from "vue";
import EmployeeService from "@/services/employee.service";
import type Employee from "@/types/employee";
import { useToast } from "primevue/usetoast";

onMounted(() => {
  employeeService.value.getAll().then((data) => (employees.value = data));
});
const toast = useToast();
const employeeService = ref(new EmployeeService());
const employees: Ref<Employee[]> = ref([]);
const employee: Ref<Employee> = ref({} as Employee);
const submitted = ref(false);
const employeeDialog = ref(false);
const deleteEmployeeDialog = ref(false);
const deleteEmployeesDialog = ref(false);
const selectedEmployees = ref();
const onRowEditSave = (event: { newData: any; index: any }) => {
  let { newData, index } = event;

  employees.value[index] = newData;
};
const openNew = () => {
  submitted.value = false;
  employeeDialog.value = true;
};
const hideDialog = () => {
  employeeDialog.value = false;
  submitted.value = false;
};
const saveEmployee = () => {
  submitted.value = true;
  if (employee.value.name.trim()) {
    if (employee.value.id) {
      employees.value[findIndexById(employee.value.id)] = employee.value;
      toast.add({
        severity: "success",
        summary: "Successful",
        detail: "Employee Updated",
        life: 3000,
      });
    } else {
      employee.value.id = createId();
      employees.value.push(employee.value);
      toast.add({
        severity: "success",
        summary: "Successful",
        detail: "Employee Created",
        life: 3000,
      });
    }

    employeeDialog.value = false;
    employee.value = {} as Employee;
  }
};
const findIndexById = (id: number) => {
  let index = -1;
  for (let i = 0; i < employees.value.length; i++) {
    if (employees.value[i].id === id) {
      index = i;
      break;
    }
  }

  return index;
};
const createId = () => {
  const ids = employees.value.map((employee) => {
    return employee.id;
  });
  if (ids.length > 0) {
    return Math.max(...ids) + 1;
  }
  return 0;
};
const editEmployee = (empl: Employee) => {
  employee.value = { ...empl };
  employeeDialog.value = true;
};
const confirmDeleteEmployee = (empl: Employee) => {
  employee.value = empl;
  deleteEmployeeDialog.value = true;
};
const deleteProduct = () => {
  employees.value = employees.value.filter(
    (val) => val.id !== employee.value.id
  );
  deleteEmployeeDialog.value = false;
  employee.value = {} as Employee;
  toast.add({
    severity: "success",
    summary: "Successful",
    detail: "Product Deleted",
    life: 3000,
  });
};
const confirmDeleteSelected = () => {
  deleteEmployeesDialog.value = true;
};
const deleteSelectedEmployees = () => {
  employees.value = employees.value.filter(
    (val) => !selectedEmployees.value.includes(val)
  );
  deleteEmployeesDialog.value = false;
  selectedEmployees.value = null;
  toast.add({
    severity: "success",
    summary: "Successful",
    detail: "Products Deleted",
    life: 3000,
  });
};
</script>

<template>
  <MainTitle>Admin</MainTitle>
  <ToolBar class="mb-4">
    <template #start>
      <PButton
        label="New Employee"
        icon="pi pi-plus"
        class="p-button-success mr-2"
        @click="openNew"
      />
      <PButton
        label="Delete"
        icon="pi pi-trash"
        class="p-button-danger"
        @click="confirmDeleteSelected"
        :disabled="!selectedEmployees || !selectedEmployees.length"
      />
    </template>
  </ToolBar>
  <DataTable
    :value="employees"
    editMode="row"
    v-model:selection="selectedEmployees"
    dataKey="id"
    @row-edit-save="onRowEditSave"
    responsiveLayout="scroll"
  >
    <PColumn
      selectionMode="multiple"
      style="width: 3rem"
      :exportable="false"
    ></PColumn>
    <PColumn field="id" header="Id" style="width: 30%">
      <template #editor="{ data, field }">
        <InputText v-model="data[field]" autofocus />
      </template>
    </PColumn>
    <PColumn field="name" header="Name" style="width: 30%">
      <template #editor="{ data, field }">
        <InputText v-model="data[field]" />
      </template>
    </PColumn>
    <PColumn field="workTime" header="Planned Work Time" style="width: 30%">
      <template #editor="{ data, field }">
        <InputText v-model="data[field]" />
      </template>
    </PColumn>
    <PColumn :exportable="false" style="min-width: 8rem">
      <template #body="slotProps">
        <PButton
          icon="pi pi-pencil"
          class="p-button-rounded p-button-success mr-2"
          @click="editEmployee(slotProps.data)"
        />
        <PButton
          icon="pi pi-trash"
          class="p-button-rounded p-button-warning"
          @click="confirmDeleteEmployee(slotProps.data)"
        />
      </template>
    </PColumn>
  </DataTable>
  <PDialog
    v-model:visible="employeeDialog"
    :style="{ width: '450px' }"
    header="Product Details"
    :modal="true"
    class="p-fluid"
  >
    <div class="field">
      <label for="name">Name</label>
      <InputText
        id="name"
        v-model.trim="employee.name"
        required="true"
        autofocus
        :class="{ 'p-invalid': submitted && !employee.name }"
      />
      <small class="p-error" v-if="submitted && !employee.name"
        >Name is required.</small
      >
    </div>
    <div class="formgrid grid">
      <div class="field col">
        <label for="workTime">Planned Work Time</label>
        <InputNumber id="workTime" v-model="employee.targetTime" integeronly />
      </div>
    </div>
    <template #footer>
      <PButton
        label="Cancel"
        icon="pi pi-times"
        class="p-button-text"
        @click="hideDialog"
      />
      <PButton
        label="Save"
        icon="pi pi-check"
        class="p-button-text"
        @click="saveEmployee"
      />
    </template>
  </PDialog>

  <PDialog
    v-model:visible="deleteEmployeeDialog"
    :style="{ width: '450px' }"
    header="Confirm"
    :modal="true"
  >
    <div class="confirmation-content">
      <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
      <span v-if="employee"
        >Are you sure you want to delete <b>{{ employee.name }}</b
        >?</span
      >
    </div>
    <template #footer>
      <PButton
        label="No"
        icon="pi pi-times"
        class="p-button-text"
        @click="deleteEmployeeDialog = false"
      />
      <PButton
        label="Yes"
        icon="pi pi-check"
        class="p-button-text"
        @click="deleteProduct"
      />
    </template>
  </PDialog>

  <PDialog
    v-model:visible="deleteEmployeesDialog"
    :style="{ width: '450px' }"
    header="Confirm"
    :modal="true"
  >
    <div class="confirmation-content">
      <i class="pi pi-exclamation-triangle mr-3" style="font-size: 2rem" />
      <span v-if="employee"
        >Are you sure you want to delete the selected employees?</span
      >
    </div>
    <template #footer>
      <PButton
        label="No"
        icon="pi pi-times"
        class="p-button-text"
        @click="deleteEmployeesDialog = false"
      />
      <PButton
        label="Yes"
        icon="pi pi-check"
        class="p-button-text"
        @click="deleteSelectedEmployees"
      />
    </template>
  </PDialog>
</template>

<style scoped></style>
