<template>

    <h1 class="page-title">GROCERY LIST</h1>

    <!-- Maybe we add a cute icon here -->
    <grocery-list-card v-bind:groceryList="groceryList" />

</template>

<script>
import GroceryListCard from '../components/GroceryListCard.vue';
import GroceryService from '../services/GroceryService';


export default {
    components: {
        GroceryListCard
    },
    data() {
        return {
            groceryList: []
        }
    },

    methods: {
        getGroceryList() {
            console.log("get card details")
            GroceryService.getGroceryList()
                .then((response => {
                    this.groceryList = response.data
                }))
                .catch((error => {
                    this.handleErrorResponse(error);
                }));
        },
        handleErrorResponse(error) {
            if (error.response.status == 404) {
                this.$router.push({ name: 'NotFoundView' });
            } else {
                // this.isLoading = false;
                this.$store.commit('SET_NOTIFICATION', `Could not get message data from server.`);
            }
        },
    },

    created() {
        this.getGroceryList();
    }
}


</script>
<style scoped>
/* .grocery-list-container {
    display: flex;
    flex-direction: column;
    margin: 0 auto;
} */
</style>
