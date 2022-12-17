<script setup lang="ts">
import authHeader from "../services/auth.header";
import TimestampService from "../services/timestamp.service";
import { ref, type Ref, onMounted } from "vue";
import type TimeStamp from "@/types/timestamp";
import MainTitle from "@/components/MainTitle.vue";

onMounted(() => {
  timestampService.value
    .getTimestamps()
    .then((res: TimeStamp[]) => (timestamps.value = res));
});

const timestamps: Ref<TimeStamp[]> = ref([]);
const timestampService = ref(new TimestampService());

function addTimeStamp() {
  const headers = authHeader();
  if (headers.Authorization) {
    fetch("/api/timestamps/add", {
      method: "POST",
      headers,
    })
      .then((res) => res.json())
      .then((data) => timestamps.value.push(data));
  }
}

function getTotalWorkHours(): number {
  let hours = 0.0;
  if (timestamps.value.length % 2 === 0) {
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
</script>

<template>
  <MainTitle>Daily Timesheet</MainTitle>
  <div>{{ new Date() }}</div>
  <!--  <div>Logged in as {{ employee.surname }}</div>-->

  <DataTable :value="timestamps" responsiveLayout="scroll" class="mb-5">
    <PColumn field="id" header="Id"></PColumn>
    <PColumn field="timestamp" header="Timestamp"></PColumn>
    <template #footer v-if="getTotalWorkHours() !== -1">
      Total work hours: {{ getTotalWorkHours().toFixed(2) }}
    </template>
  </DataTable>
  <PButton @click="addTimeStamp()">Add Timestamp</PButton>
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
