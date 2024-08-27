# De Cuisine: Meal Planner

De Cuisine is a full-stack meal planning application designed to simplify the process of meal plan creation, recipe management, and grocery list integration. It also features visual tracking with FullCalendar.io, providing users with an intuitive interface to organize and manage their meal plans. De Cuisine was created using Agile principles and functioned as a final capstone project for Tech Elevator.

## Technologies Used

- **Frontend**: Vue.js
- **Backend**: Java with Spring Boot
- **Database**: PostgreSQL
- **Additional Libraries/Packages**:
  - `@fullcalendar/vue3`
  - `@fullcalendar/daygrid`
  - `@fullcalendar/interaction`
  - `primevue`

## Features

1. **User Authentication**:
   - Users can log in to access their account data.
   - New users can register and create an account to start adding recipes and creating meal plans.

2. **Recipe Management**:
   - Users can add new recipes to their personal library.
   - Recipes consist of a list of ingredients and preparation instructions.
   - Users can view, edit, and manage their saved recipes.

3. **Ingredient Management**:
   - Users can add new ingredients to the system if the desired ingredient is not already listed.

4. **Meal Plan Creation**:
   - Users can create meal plans by selecting recipes from their library.
   - Meal plans can be customized and tracked using FullCalendar.io.

5. **Grocery List Integration**:
   - Automatically generate a grocery list based on the ingredients in the user's meal plan.
   - Users can manage and track their grocery items.

6. **Visual Tracking**:
   - FullCalendar.io is integrated for meal plan visualization, allowing users to easily track and manage their meals over time.

## ERD

- Please refer to the ERD (Entity Relationship Diagram) provided to understand the database structure and relationships.

## API Endpoints

Refer to the following endpoints for interacting with the backend:

### Authentication
- **POST** `/register`
- **POST** `/login`

### Recipes
- **GET** `/recipes`
- **POST** `/recipes`
- **GET** `/recipes/{recipeId}`
- **PUT** `/recipes/{recipeId}`
- **DELETE** `/recipes/{recipeId}`

### Meal Plans
- **GET** `/mealplan`
- **POST** `/mealplan`
- **GET** `/mealplan/{mealplanId}`
- **PUT** `/mealplan/{mealplanId}`
- **DELETE** `/mealplan/{mealplanId}`

### Grocery Lists
- **GET** `/grocerylist`
- **POST** `/grocerylist`
- **PUT** `/grocerylist`
- **DELETE** `/grocerylist`

### Tracked Meals
- **GET** `/tracked`
- **POST** `/tracked`
- **PUT** `/tracked`
- **DELETE** `/tracked/{mealplanId}`



- The list of API endpoints has been provided for reference in managing the application.

## Common Issues & Solutions

1. **401 Status Error**:
   - Update the Bearer Token to resolve the issue.

2. **500/501 Error**:
   - Rerun the GRANT permissions in pgAdmin (user_STEP2.sql).

3. **500 Internal Server Error**:
   - Restart and rerun the server in Java.

## Setting Up Your Database

To set up your database, follow these steps:

1. Open a terminal.
2. Navigate to the project database folder within the Java folder:
   ```bash
   cd <project-root>/java/database/
   ./create.sh
3. Open pgAdmin.
4. Create the database "final_capstone" using the provided scripts.
5. Right-click on the database and open the Query Tool.
6. Update the database using the following steps:
    - Git pull the latest changes.
    - In pgAdmin, open the Query Tool:
        - Run schema_step1.sql
        - Run data_step2.sql
    - Manually create the user user2024 in PostgreSQL.
7. Register User
POST: localhost:9000/register

{
  "username": "user2024",
  "password": "user2024",
  "confirmPassword": "user2024",
  "role": "ROLE_ADMIN"
}

8. Log in to obtain the AuthToken for testing in PostgreSQL:
{
  "username": "user2024",
  "password": "user2024"
}
9. Continue with:
    - Running demotestdata_step3.sql
    - Running user_step4.sql
10. Refresh your tables in the final_capstone database.
11. In pgAdmin, rerun step_4.sql.
12. Ensure that you have executed the 'GRANT permissions' for all tables.
13. Restart the Java server.
14. Start the Vue.js frontend.

Your database should now have the user2024 account available for demo purposes.

*Note* You may create other users and those users may add any recipes or mealplans to the site. 

## Conclusion

De Cuisine: Meal Planner offers a comprehensive solution for meal planning, recipe management, and grocery list integration. This README file outlines the steps necessary to set up and run the application successfully. If you encounter any issues, please refer to the "Common Issues & Solutions" section for troubleshooting assistance.
