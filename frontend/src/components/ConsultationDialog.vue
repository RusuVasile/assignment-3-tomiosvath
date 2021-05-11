<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "Create item" : "Operations" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model="consultation.date" label="Date ('yr-mm-dd hh')" />
                    <v-text-field v-model="consultation.patientName" label="Patient's name" />
                    <v-text-field v-model="consultation.doctorName" label="Doctor's name" />
                    <v-text-field v-model="consultation.author" label="Author " />
                    <v-text-field v-model="consultation.details" label="Details" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteConsultation">Delete Consultation</v-btn>
                    <v-btn @click="patientArrived">Check in patient</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "ConsultationDialog",
        props: {
            consultation: Object,
            opened: Boolean,

        },
        data() {
            return {
                connected: false,
            }
        },
        methods: {
            persist() {
                if (this.isNew){
                    api.consultations.create({
                        date: this.consultation.date,
                        patientName: this.consultation.patientName,
                        doctorName: this.consultation.doctorName,
                        author: this.consultation.author,
                        details: this.consultation.details,
                    })
                    .then(() => this.$emit("refresh"));
                } else {
                    api.consultations.edit({
                        id: this.consultation.id,
                        date: this.consultation.date,
                        patientName: this.consultation.patientName,
                        doctorName: this.consultation.doctorName,
                        author: this.consultation.author,
                        details: this.consultation.details,
                    }).then(() => this.$emit("refresh"));
                }
            },
            deleteConsultation(){
                api.consultations.delete({
                    id: this.consultation.id,
                })
                .then(() => this.$emit("refresh"));
            },
            patientArrived(){
                api.webSocket.checkIn(this.consultation.patientName, this.consultation.date, this.consultation.doctorName);
            },
        },
        computed: {
            isNew: function () {
                return !this.consultation.id;

            },
        },
        created() {
            this.connected = api.webSocket.connect();
        },
    };
</script>

<style scoped>

</style>