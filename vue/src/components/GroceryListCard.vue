<template>
    <div>
        <form v-on:submit.prevent="submitForm" class="grocery-checklist-form form-container grocery-card">
            <ul>
                <li v-for="(item, index) in updatedGroceryList" :key="index" class="grocery-item">
                    <input type="checkbox" v-model="item.checked">
                    {{ item.name }} amount: {{ item.amount }} unit: {{ item.unit }}
                </li>
            </ul>
            <button type="submit" id="submit-btn">Delete Checked Items</button>
            <button type="button" class="edit-btn" @click="editGroceryList">Edit Grocery List</button>
        </form>
    </div>
</template>

<script>

import GroceryService from '../services/GroceryService.js';

export default {
    data() {
        return {
            updatedGroceryList: []
        }
    },

    methods: {
        submitForm() {
            // Filter out checked
            this.updatedGroceryList = this.updatedGroceryList.filter(item => !item.checked);

            // Update gl with filtered list
            GroceryService.updateGroceryList(this.updatedGroceryList).then(response => {
                this.$router.push({ name: 'grocery-list' });
            });
        },
        editGroceryList() {
            // take to edit recipe view 
            this.$router.push({ name: 'grocery-list-edit', });
        }
    },

    created() {
        GroceryService.getGroceryList().then(response => {

            this.updatedGroceryList = response.data.map(item => ({
                ...item,
                //start with everything unchecked
                checked: false
            }));
        });
    }
}
</script>

<style scoped>
ul {
    /* margin-top: 50px; */
    list-style: none;
}

.grocery-item {
    font-size: 1.2em;
    margin-bottom: 10px;
}

#submit-btn {
    display: block;
    margin: 50px auto 0 auto;
    background-color: red;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 1.1em;
}

.grocery-card {
    position: relative;
}

.edit-btn {
    position: absolute;
    z-index: 10;
    top: 0px;
    right: 0px;
    border-radius: 2px 10px 2px 2px;
}
</style>
