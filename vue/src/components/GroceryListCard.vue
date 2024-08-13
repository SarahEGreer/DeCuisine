<template>
    <div>
        <form v-on:submit.prevent="submitForm" class="grocery-checklist-form">
            <ul>
                <li v-for="(item, index) in groceryList" :key="index">
                    <input type="checkbox" @click="checkOffItem(index)">
                    {{ item.name }}
                    amount: {{ item.amount }}
                    unit: {{ item.unit }}
                </li>
                <!-- continue making into GL specific to database -->
            </ul>
            <button type="submit" id="submit-btn">Delete Checked Items</button>
        </form>
    </div>
</template>

<script>

import GroceryService from '../services/GroceryService.js';
// import RecipeService from '../services/RecipeService.js';

export default {
    props: {
        groceryList: {
            type: Array,
            required: true
        }
    },

    data() {
        return {
            updatedGroceryList: []
        }
    },

    methods: {
        checkOffItem(index) {
            console.log(index)
            // needs to be index of updatedGroceryList not groceryList
            this.updatedGroceryList.splice(index, 1);
            console.log(this.updatedGroceryList)
        },
        submitForm() {
            GroceryService.updateGroceryList(this.updatedGroceryList).then(response => {
                this.$router.push({ name: 'grocery-list' })
            })
        }
    },

    created() {
        GroceryService.getGroceryList().then(response => {
            this.updatedGroceryList = response.data;
        })
    }
}
</script>

<style scoped>
ul {
    list-style: none;
}
</style>