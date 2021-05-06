<template>
    <v-card>
        <v-card-title>
            Patients
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="addPatient">Add patient</v-btn>
            <v-btn @click="switchView">Switch to consultations</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="patients"
                :search="search"
                @click:row="editPatient"
        ></v-data-table>
        <PatientDialog
                :opened="dialogVisible"
                :patient="selectedPatient"
                @refresh="refreshList"
        ></PatientDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import PatientDialog from "../components/PatientDialog";
    import router from "../router";

    export default {
        name: "PatientList",
        components: { PatientDialog},
        data(){
            return {
                patients: [],
                search: "",
                headers: [
                    {
                        text: "Name",
                        align: "start",
                        sortable: false,
                        value: "name",
                    },
                    { text: "ID Card Number", value: "idCardNb" },
                    { text: "Personal Code", value: "personalCode" },
                    { text: "Birth Date", value: "birthDate" },
                    { text: "Address", value: "address" },
                ],
                dialogVisible: false,
                selectedPatient: {},
            };
        },
        methods: {
            editPatient(patient) {
                this.selectedPatient = patient;
                this.dialogVisible = true;
            },
            addPatient() {
                this.dialogVisible = true;
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedPatient = {};
                this.patients = await api.patients.allPatients();
            },
            switchView(){
                router.push("/consultations");
            }
        },
        created() {
            this.refreshList();
        },
    };
</script>

<style scoped>

</style>