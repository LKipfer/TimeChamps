<script setup lang="ts">
import TimestampService from "../services/timestamp.service";
import { ref, type Ref, onMounted } from "vue";
import type TimeStamp from "@/types/timestamp";
import EmployeeService from "@/services/employee.service";
import type Employee from "@/types/employee";

onMounted(() => {
  timestampService.value
    .getTimestamps()
    .then((res: TimeStamp[]) => (timestamps.value = res));
  setInterval(() => {
    getDate();
  }, 1000);
  employeeService.value.getMe().then((res: Employee) => (employee.value = res));
});

const timestamps: Ref<TimeStamp[]> = ref([]);
const employee: Ref<Employee> = ref({} as Employee);
const timestampService = ref(new TimestampService());
const employeeService = ref(new EmployeeService());
let today = ref(new Date());

function getDate(): void {
  today.value = new Date();
}

function addTimeStamp(): void {
  timestampService.value
    .addTimestamp()
    .then((res) => timestamps.value.push(res));
}

function getTotalWorkHours(): number {
  let hours = 0.0;
  if (!getCurrentlyPunchedIn()) {
    for (let i = 0; i < timestamps.value.length; i++) {
      if (i < timestamps.value.length + 1) {
        const startTime = new Date(timestamps.value[i].timestamp).getTime();
        const endTime = new Date(timestamps.value[i + 1].timestamp).getTime();
        hours += calcWorkTime(startTime, endTime);
        i += 1;
      }
    }
    return hours;
  }
  return -1;
}

function calcWorkTime(startTime: number, endTime: number): number {
  const msInHour = 1000 * 60 * 60;
  return Math.abs(startTime - endTime) / msInHour;
}

function getGreeting(): string {
  let greeting = "Good evening";
  if (today.value.getHours() < 12) {
    greeting = "Good morning";
  } else if (today.value.getHours() < 18) {
    greeting = "Good afternoon";
  }
  return greeting;
}

function getCurrentlyPunchedIn(): boolean {
  return timestamps.value.length % 2 !== 0;
}
</script>

<template>
  <p class="text-4xl font-bold">
    {{ getGreeting() }} <span class="font-normal">{{ employee.surname }}</span>
  </p>
  <div class="bg-primary p-3 mb-5 border-round">{{ today }}</div>
  <DataTable :value="timestamps" responsiveLayout="scroll" class="mb-5">
    <PColumn field="id" header="Id"></PColumn>
    <PColumn field="timestamp" header="Timestamp"></PColumn>
    <template
      #footer
      v-if="getTotalWorkHours() !== -1 && timestamps.length > 0"
    >
      Total: {{ getTotalWorkHours().toFixed(2) }} of
      {{ employee.targetTime.toFixed(2) }} hours
    </template>
  </DataTable>
  <PButton
    @click="addTimeStamp()"
    :class="[getCurrentlyPunchedIn() ? 'p-button-danger' : 'p-button-success']"
    >{{
      getCurrentlyPunchedIn() ? "End work time" : "Start work time"
    }}</PButton
  >
</template>

<style scoped>
table {
  border-collapse: collapse;
  min-width: 400px;
  margin: 25px 0;
  font-size: 0.9em;
  border-radius: 5px 5px 0 0;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
}

th {
  background-color: #fde70e;
  vertical-align: middle;
  color: #000000;
  font-weight: bold;
}

th,
td {
  text-align: left;
  padding: 12px 15px;
}

tbody tr {
  border-bottom: 1px solid #dddddd;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

button {
  background-color: #4caf50; /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
}
</style>
