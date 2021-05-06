<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "Create Patient" : "Operations" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model = "patient.name" label = "Name" />
                    <v-text-field v-model = "patient.idCardNb" label = "ID Card No" />
                    <v-text-field v-model = "patient.personalCode" label = "Personal Code" />
                    <v-text-field v-model = "patient.birthDate" label = "Birth Date ('yr-MM-dd')" />
                    <v-text-field v-model = "patient.address" label = "Address" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deletePatient">Delete Patient</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "PatientDialog",
        props: {
            patient: Object,
            opened: Boolean,
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.patients
                        .create({
                            name: this.patient.name,
                            idCardNb: this.patient.idCardNb,
                            personalCode: this.patient.personalCode,
                            birthDate: this.patient.birthDate,
                            address: this.patient.address,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.patients
                        .edit({
                            id: this.patient.id,
                            name: this.patient.name,
                            idCardNb: this.patient.idCardNb,
                            personalCode: this.patient.personalCode,
                            birthDate: this.patient.birthDate,
                            address: this.patient.address,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            deletePatient(){
                api.patients.delete({
                    id: this.patient.id,
                })
                    .then(() => this.$emit("refresh"));
            },
        },
        computed: {
            isNew: function () {
                return !this.patient.id;
            },
        },
    }
</script>

<style scoped>

</style>