<template>
    <mealplan-card :mealplan="mealplan" />
</template>

<script>
import MealplanCard from '../components/MealplanCard.vue';
import MealplanService from '../services/MealplanService.js';

export default {
    components: {
        MealplanCard
    },
    data() {
        return {
            mealplan: {}
        }
    },
    methods: {
        getMealplanByMealplanId(mealplanId) {
            MealplanService.getMealplanDetailsById(mealplanId)
                .then(response => {
                    this.mealplan = response.data;
                })
                .catch(error => {
                    this.handleErrorResponse(error);
                })
        },
        handleErrorResponse(error) {
            if (error.response.status == 404) {
                this.$router.push({ name: 'NotFoundView' });
            } else {
                this.isLoading = false;
                this.$store.commit('SET_NOTIFICATION', `Could not get message data from server.`);
            }
        }
    },
    created() {
        this.getMealplanByMealplanId(this.$route.params.mealplanId);
    }
}

</script>