<template>
    <div>
        <form v-on:submit.prevent="submitForm" class="grocery-checklist-form">
            <ul>
                <li v-for="(item, index) in updatedGroceryList" :key="index" class="grocery-item">
                    <input type="checkbox" v-model="item.checked">
                    {{ item.name }} amount: {{ item.amount }} unit: {{ item.unit }}
                </li>
            </ul>
            <button type="submit" id="submit-btn">Delete Checked Items</button>
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
    margin-top: 50px;
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

</style>
