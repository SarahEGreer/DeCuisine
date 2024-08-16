<template>
  <div class="about-page">
    <div class="about-hero">
      <!-- <img src="@/assets/pexels-photo-1640777.jpeg" alt="About De Cuisine" class="hero-image"> -->
      <img src="src\assets\pexels-micheile-8366815.jpg" alt="About De Cuisine" class="hero-image">
      <div class="hero-overlay">
        <h1 class="about-title">About <span class="title-brand"> De Cuisine</span></h1>
        <h2 class="about-subtitle">Because Every Meal Deserves A Plan</h2>
      </div>
    </div>

    <div class="about-body">

      <div class="about-content">
        <p>
          Welcome to De Cuisine, the ultimate meal planning companion designed to simplify your kitchen experience.
          Whether you're a seasoned chef or just starting your culinary journey, our app is here to help you plan,
          create,
          and manage your meals with ease.
        </p>
      </div>

      <div class="features-section">
        <h2 class="section-title">Features</h2>
        <div class="features-grid">
          <div class="feature-card">
            <router-link to="/recipes/new" class="feature-link">
              <h3>Recipe Creation</h3>
              <p>Easily create and store your favorite recipes with De Cuisine.</p>
              <img src="src\assets\Sceenshots\CreateRecipe.png" alt="Recipe Creation" class="feature-image">
            </router-link>
          </div>
          <div class="feature-card">
            <router-link to="/mealplans/new" class="feature-link">
              <div class="feature-card-container">
                <h3>Meal Plan Creation</h3>
                <p>Organize and customize your weekly or monthly meals effortlessly with De Cuisine's intuitive planning
                  feature.</p>
                <img src="src\assets\Sceenshots\CreateMealPlan.png" alt="Meal Plan Creation" class="feature-image">
              </div>
            </router-link>
          </div>
          <div class="feature-card">
            <router-link to="/grocerylist" class="feature-link">
              <div class="feature-card-container">
                <h3>Grocery List</h3>
                <p>Automatically generate a shopping list based on your meal plans, saving you time and keeping you
                  organized.
                </p>
                <video autoplay loop muted>
                  <source src="src\assets\Sceenshots\GroceryList.mp4" type="video/mp4" alt="Grocery List"
                    class="feature-video">
                </video>
              </div>
            </router-link>
          </div>
          <div class="feature-card">
            <router-link to="/mealplans/scheduler" class="feature-link">
              <div class="feature-card-container">
                <h3>Meal Plan Scheduler</h3>
                <p>Schedule balanced meals ahead of time with our user-friendly planning tool.</p>
                <video autoplay loop muted>
                  <source src="@\assets\Sceenshots\calendar.mp4" type="video/mp4" alt="Meal Plan Scheduler"
                    class="feature-video">
                </video>
              </div>
            </router-link>
          </div>
          <div class="feature-card">
            <router-link to="/recipes/list" class="feature-link">
              <div class="feature-card-container">
                <h3>Recipe Library</h3>
                <p>Easily access and manage your saved recipes for quick addition to your meal plans.</p>
                <video autoplay loop muted>
                  <source src="@\assets\Sceenshots\RecipeList.mp4" type="video/mp4" alt="Recipe Library"
                    class="feature-video">
                </video>
              </div>
            </router-link>
          </div>
          <div class="feature-card">
            <router-link to="/register" class="feature-link">
              <div class="feature-card-container">
                <h3>User Friendly</h3>
                <p>Our clean and intuitive design makes meal planning simple and enjoyable.</p>
                <img src="@\assets\Stock-Images\UserFriendly.jpg" alt="User Friendly" class="feature-image">
              </div>
            </router-link>
          </div>
        </div>
      </div>


      <h2 class="section-title">How De Cuisine works?</h2>
      <div class="how-it-works">
        <p>
          Getting started with De Cuisine is simple. Create an account, start adding your favorite recipes, and build
          meal
          plans in just a few clicks. Our grocery list generator will help you stay organized, and the meal plan
          scheduler
          will keep your kitchen running smoothly.
        </p>
        <a href="/register" class="light-link">Register for an account here!</a>
      </div>

      <div class="testimonials-section">
        <h2 class="section-title">What our users say?</h2>
        <div class="testimonials-grid">
          <div class="testimonial-card">
            <p>"Even as a professional chef, De Cuisine has become an essential tool in my kitchen.
              It helps me organize and plan my meals efficiently, especially when balancing work and home cooking. The
              streamlined grocery list feature keeps me on track, and I love having all my meal plans in one place. It's
              a
              great resource, even for those of us who cook for a living!"</p>
            <h3 class="testimonial-name">- Tom K., Professional Chef</h3>
          </div>
          <div class="testimonial-card">
            <p>"De Cuisine has been a game-changer for my budgeting. With the app,
              I can plan out my meals for the week and keep track of exactly what I need,
              which has helped me cut down on unnecessary grocery expenses.
              It’s amazing how much I’ve saved by being more organized with my meal planning!"</p>
            <h3 class="testimonial-name">- Jamie L., College Student</h3>
          </div>
          <div class="testimonial-card">
            <p>"As a parent with a hectic schedule, De Cuisine has been a lifesaver.
              I can plan meals for the entire family in advance, and the grocery list feature ensures
              I have everything I need without making multiple trips to the store. It's made dinnertime so much less
              stressful!"</p>
            <h3 class="testimonial-name">- Megan M., Busy Parent</h3>
          </div>
        </div>
      </div>

      <div class="who-are-we-section">
        <h2 class="section-title">Who are we?</h2>

        <div class="team-member">
          <div class="bio-content">
            <img src="@\assets\TeamPics\Bryan_Freedman_Headshot.jpg" alt="Team Member 1" class="team-image">
            <div class="bio-text">
              <h3>Bryan Freedman</h3>
              <p class="role">Jr. Software Developer</p>
              <a href="https://www.linkedin.com/in/bryanfreedmancreative/" class="linkedin-link" target="_blank"
                rel="noopener noreferrer">LinkedIn</a>
              <p class="bio">Bryan is a software developer transitioning from a diverse background in the arts,
                education,
                and leadership.
                With experience ranging from developing groundbreaking musical theatre, to coordinating UN conferences,
                to
                leading robotics teams,
                Bryan now combines his passion for technology with problem-solving and collaboration skills honed
                through
                years of creative and advocacy work.
                He is eager to leverage his unique perspective and growing skill in software engineering to build
                beautiful tools that make the world better and more accessible.</p>
            </div>
          </div>
        </div>

        <div class="team-member reverse">
          <div class="bio-content">
            <img src="@\assets\TeamPics\Sarah_Pic.jpg" alt="Team Member 2" class="team-image">
            <div class="bio-text">
              <h3>Sarah Greer</h3>
              <p class="role">Jr. Software Developer</p>
              <a href="https://www.linkedin.com/in/sarahgreerdeveloper/" class="linkedin-link" target="_blank"
                rel="noopener noreferrer">LinkedIn</a>
              <p class="bio">Sarah is a Software Developer with a passion for responsive design, accessibility, and
                clean
                code.
                Her journey from educator to developer has equipped her with top-notch communication skills, strong
                organizational abilities,
                and a deep commitment to inclusion. She aims to build technology that fosters a more connected and
                accessible world.</p>
            </div>
          </div>
        </div>

        <div class="team-member">
          <div class="bio-content">
            <img src="@\assets\TeamPics\David_Pic.jpg" alt="Team Member 3" class="team-image">
            <div class="bio-text">
              <h3>David Hall</h3>
              <p class="role">Jr. Software Developer</p>
              <a href="https://www.linkedin.com/in/davidhalljrdev/" class="linkedin-link" target="_blank"
                rel="noopener noreferrer">LinkedIn</a>
              <p class="bio">David is a passionate software developer with a strong background in communication and
                collaboration honed in retail with a focus on data driven design patterns and building robust back-end
                systems utilizing tools like PostgreSQL, and Spring Boot.</p>
            </div>
          </div>
        </div>

        <div class="team-member reverse">
          <div class="bio-content">
            <img src="@\assets\TeamPics\Karyssa_Pic.jpg" alt="Team Member 4" class="team-image">
            <div class="bio-text">
              <h3>Karyssa Miller</h3>
              <p class="role">Jr. Software Developer</p>
              <a href="https://www.linkedin.com/in/karyssamiller/" class="linkedin-link" target="_blank"
                rel="noopener noreferrer">LinkedIn</a>
              <p class="bio">Karyssa transitioned from a Senior Product Analyst role to full-stack development, focusing
                on Java, Spring Boot, JavaScript, and Vue.js.
                With a background in marketing, e-commerce, product design, API integration, database design, and
                automation, she is committed to creating user-friendly
                and innovative software solutions. Karyssa is excited to contribute to a collaborative team and make a
                meaningful impact in software development.</p>
            </div>
          </div>
        </div>

        <div class="team-member">
          <div class="bio-content">
            <img src="@\assets\TeamPics\Kim_Pic.jpg" alt="Team Member 5" class="team-image">
            <div class="bio-text">
              <h3>Kim Thilavanh</h3>
              <p class="role">Jr. Software Developer</p>
              <a href="https://www.linkedin.com/in/kimberly-thilavanhdeveloper/" class="linkedin-link" target="_blank"
                rel="noopener noreferrer">LinkedIn</a>
              <p class="bio">Kim is a resilient professional known for adapting to challenges and delivering results,
                as shown during their role as a Buyer where they played a key role in refactoring a supply chain model
                amidst a global crisis.
                Now transitioning into tech, they bring a collaborative spirit and a passion for innovation, eager to
                contribute and grow in a dynamic environment.
                Connect with Kim to explore how they can help drive innovation and success.</p>
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>


<script>
export default {};
</script>

<style scoped>
.about-body {
  width: 90%;
  max-width: 1200px;
  margin: 0 auto;
}

/* hero */
.about-hero {
  position: relative;
  text-align: center;
  color: white;
  margin-bottom: 20px;
  width: 100%;
  z-index: 1;
}

.hero-image {
  width: 100%;
  height: 400px;
  object-fit: cover;
  margin-bottom: 0;
}

.hero-overlay {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: rgba(0, 0, 0, .8);
  padding: 20px 40px;
  border-radius: 10px;
}

.about-title,
.about-subtitle {
  color: white;
}

.title-brand {
  font-size: 6rem;
  color: white;
  font-weight: 500;
}

.about-title {
  font-size: 6rem;
  margin: 0;
  font-weight: 300;
}

.about-subtitle {
  font-size: 1.7rem;
  margin: 0;
  font-weight: 300;
}

.about-content p {
  text-align: center;
  margin: 35px 0;
  font-size: 1.75em;
  color: #333;
}

/* features */
.features-section {
  text-align: center;
  margin: 20px 0;
}

.features-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
  justify-content: center;
}

.feature-card-container {
  width: 90%;
  margin: 0 auto;

}

.feature-card {
  color: #fff;
  /* background-color: #f7d9c4; */
  background-color: #AEC2B8;
  padding: 20px 0;
  border-radius: 10px;
  margin: 0px 0px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  min-height: 400px;
}

.feature-card:nth-child(even) {
  background-color: #C5D3CC;
}

.feature-card h3 {
  margin-top: 10px;
  font-size: 2em;
  color: #151414;
}

.feature-card p {
  margin-top: -10px;
  font-size: 1.5em;
  color: #000;
}

.feature-image {
  margin-top: 15px;
  /* width: 300px;
  height: 300px; */
  width: 80%;
  object-fit: contain;
}

.feature-link {
  color: #333;
  text-decoration: none;
}

/* .feature-link:hover {
  opacity: 0.8;
} */

.section-title {
  margin-top: 80px;
  font-size: 2.2rem;
  color: #333;
  text-align: center;
}

.how-it-works {
  /* background-color: #a3d6ff; */
  background-color: #A85053;
  color: white;
  padding: 20px;
  border-radius: 10px;
  text-align: center;
}

.how-it-works p {
  margin: 20px 0;
  font-size: 1.5em;
  color: #fff;
}

.how-it-works a {
  /* color: #0000ff; */
  text-decoration: underline;
  font-size: 1.5em;
}

.testimonials-section {
  text-align: center;
  margin: 20px 0;
}

.testimonials-section p {
  margin: 20px 0;
  font-size: 1.25em;
  color: #fff;
}

.testimonials-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 60px;
}

.testimonial-card {
  /* background-color: #f7d9c4; */
  background-color: #627360;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.testimonial-name {
  color: #fff;
}

.who-are-we-section {
  text-align: center;
  margin: 40px 0;
}

.team-member {
  display: flex;
  align-items: center;
  margin: 20px 0;
}

.team-member.reverse {
  flex-direction: row-reverse;
}

.bio-content {
  display: flex;
  align-items: center;
  max-width: 80%;
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.team-image {
  width: 150px;
  height: 150px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 25px;
  margin-left: 10px;
}

.team-member.reverse .team-image {
  margin-left: 10px;
  margin-right: 25px;
}

.linkedin-link {
  display: block;
  margin-top: 5px;
  color: #0073b1;
  text-decoration: none;
  font-weight: bold;
  font-size: 1.3em;
}

.bio-text {
  text-align: left;
  color: #333;
}

.bio-text h3 {
  font-size: 2em;
  margin-top: 10px;
  margin-bottom: 5px;
}

.bio-text p {
  margin-top: 15px;
  font-size: 1.3em;
}


.role {
  font-weight: bold;
  color: #666;
  margin: 5px 0;
  font-size: 1em;
}

/* bio is body text */
.bio {
  color: #333;
  font-size: 1.25em;
}

video {
  /* width: 460px;
  height: 300px; */
  width: 80%;
  border-radius: 10px;
  margin: auto;
}
</style>
