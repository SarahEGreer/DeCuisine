<template>
    <h1>GROCERY LIST</h1>
    <!-- Maybe we add a cute icon here -->
    <grocery-list-card v-bind:groceryList="groceryList" />
    <button @click="editGroceryList" id="edit-btn">Edit Grocery List</button>


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
        editGroceryList() {
            // take to edit recipe view 
            this.$router.push({ name: 'grocery-list-edit', });
        }
    },

    created() {
        this.getGroceryList();
    }
}


</script>
<style scoped>
#edit-btn {
    margin-bottom: 5em;
}
</style>
