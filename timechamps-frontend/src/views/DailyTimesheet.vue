<template>
  <Title>Daily Timesheet</Title>
  <div>{{ new Date() }}</div>
  <div>Logged in as {{ employee.surname }}
  </div>
  <table>
    <thead>
    <tr>
      <th>Id</th>
      <th>Start Time</th>
    </tr>
    </thead>
    <tbody>
    <tr v-for="timestamp in data" :key="timestamp.id">
      <td>{{ timestamp.id }}</td>
      <td>{{ timestamp.timestamp }}</td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
      <th id="total">Total Work</th>
<!--      <th id="total">{{ calcWorkHours(data.map(data => data.workTime)) }}</th>-->
    </tr>
    </tfoot>
  </table>
  <button  v-on:click="addTimeStamp()">Add Timestamp</button>
</template>

<script setup lang="ts">
import Title from '../components/Title.vue';
import {Employee} from "../model/employee";
import {TimeStamp} from "../model/timestamp";

//const {data} = useFetch("/timestamps/all")
const employee: Employee = {
  id: 0,
  surname: "Isabelle",
  name: '',
  address: '',
  place: '',
  jobTitle: '',
  email: '',
  employeeCode: ''
};
const data: TimeStamp[] = [
  {
    id: 0,
    timestamp: new Date("2022-12-10T08:00:00.000Z")
  },
  {
    id: 1,
    timestamp: new Date("2022-12-10T13:00:00.000Z"),
  },
];

function addTimeStamp() {
  // http://localhost:8080/timestamps/add
  fetch('/api/timestamps/add', {
    method: 'POST',
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('access_token')
    }
  })
      .then(res => res.json())
      .then(data => console.log(data))
}

function calcWorkTime(startTime: number, endTime: number): number {
  const msInHour = 1000 * 60 * 60;
  return Math.round(Math.abs(startTime - endTime) / msInHour);
}

function calcWorkHours(numbers: number[]): number {
  let count = 0;
  for (const number of numbers) {
    count += number;
  }
  return count;
}
</script>

<style scoped>
table {
  border-collapse: collapse;
  min-width: 400px;
  margin: 25px 0;
  font-size: 0.9em;
  border-radius: 5px 5px 0 0;
  overflow: hidden;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
  background-color: #202e39;
  color: white;
}

th {
  background-color:royalblue;
  color: white;
  vertical-align: middle;
  font-weight: bold;
}


th, td {
  text-align: left;
  padding: 12px 15px;
}

tbody tr{
border-bottom: 1px solid #dddddd;

}

tr:nth-child(even) {
  background-color: #364a5a;
}

button {
  display: inline-block;
  text-decoration: none;
  padding: 15px 32px;
  font-size: 16px;
  border: none;
  text-align: center;

  background-color: #000000;
  color: white;
  border-radius: 30px;
  transition: transform 0.4s ease;
  
}

button::after, button::before{
content: "";
position: absolute;
opacity: 0.3;
width: 100%;
height: 100%;
left: 0;
bottom: 0;
z-index: -1;
transition: transform 0.3s ease;

}

button:hover{
  transform: translate(-6px, -6px);
}

button:hover::after{
  transform: translate(3px, 3px);
}

button:hover::before{
  transform: translate(9px, 9px);
}
</style>
