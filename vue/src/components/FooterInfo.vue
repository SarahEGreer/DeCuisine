<template>
    <div class="footer">
        <img class="footer-logo" src="../assets/DE_CUISINE_LOGO_WHITE.png" alt="">
        <span class="footer-tagline">Because Every Meal Deserves A Plan&#8482;</span>
        <span class="footer-copy">&copy; 2024</span>
    </div>
</template>

<style scoped>
.footer {
    background-color: #000;
    padding: 17px;
    display: flex;
    flex-direction: column;
    /* justify-content: center; */
    align-items: center;

    gap: 5px;
}

.footer-copy,
.footer-tagline {
    color: #fff;
    font-weight: 200;
}

.footer-copy {
    font-size: 12px;
    margin-top: 7px;
}

.footer-tagline {
    font-size: 15px;
}

.footer-logo {
    width: 100px;
}
</style>