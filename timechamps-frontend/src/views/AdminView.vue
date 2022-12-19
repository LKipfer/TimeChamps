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
const editingRows = ref([]);
const employee: Ref<Employee> = ref({} as Employee);
const submitted = ref(false);
const employeeDialog = ref(false);
const onRowEditSave = (event: { newData: any; index: any }) => {
  let { newData, index } = event;

  employees.value[index] = newData;
};
const openNew = () => {
  submitted.value = false;
  employeeDialog.value = true;
  console.log(employee.value);
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
      employee.value.employeeCode = createCode();
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
  return 2;
};
const createCode = () => {
  return "";
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
    </template>
  </ToolBar>
  <DataTable
    :value="employees"
    editMode="row"
    dataKey="id"
    v-model:editingRows="editingRows"
    @row-edit-save="onRowEditSave"
    responsiveLayout="scroll"
  >
    <PColumn field="employeeCode" header="Code" style="width: 30%">
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
    <PColumn
      :rowEditor="true"
      style="width: 10%; min-width: 8rem"
      bodyStyle="text-align:center"
    ></PColumn>
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
</template>

<style scoped></style>
