document.addEventListener("DOMContentLoaded", function () {
    setupButtons();
    createStars();
    setupCardSwitching(); // Ενεργοποιεί την εναλλαγή καρτών
    setupFlashlightEffect();


    window.addEventListener("resize", function () {
        createStars();
    });

    // Διαχείριση του "Scroll Down" κουμπιού
    const scrollButton = document.getElementById('scrollDownButton');

    // Ακροατής για το πάτημα του κουμπιού
    scrollButton.addEventListener('click', function () {
        // Κύλιση προς τα κάτω
        window.scrollBy({
            top: window.innerHeight, // Προχωράει όσο το ύψος της οθόνης
            behavior: 'smooth' // Η κύλιση να είναι ομαλή
        });
    });

    // Έλεγχος του scroll για να εξαφανιστεί το κουμπί όταν φτάσουμε στο τέλος
    window.addEventListener('scroll', function () {
        if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
            scrollButton.classList.add('hidden');
        } else {
            scrollButton.classList.remove('hidden');
        }
    });
});

function createStars() {
    let starContainer = document.querySelector(".stars");

    // Αν υπάρχει ήδη, διαγράφουμε τα προηγούμενα αστέρια
    if (starContainer) {
        starContainer.innerHTML = "";
    } else {
        starContainer = document.createElement("div");
        starContainer.classList.add("stars");
        document.body.appendChild(starContainer);
    }

    // Ορίζουμε το starContainer να καλύπτει όλη την οθόνη
    starContainer.style.position = "fixed";
    starContainer.style.top = "0";
    starContainer.style.left = "0";
    starContainer.style.width = "100vw";
    starContainer.style.height = "100vh";
    starContainer.style.overflow = "hidden";
    starContainer.style.zIndex = "-1"; // Φέρνει τα αστέρια πίσω από το περιεχόμενο

    const numStars = Math.floor(window.innerWidth / 15) + 150; // Περισσότερα αστέρια για πυκνότητα

    for (let i = 0; i < numStars; i++) {
        let star = document.createElement("div");
        star.classList.add("star");

        let x = Math.random() * window.innerWidth;
        let y = Math.random() * window.innerHeight;
        star.style.left = `${x}px`;
        star.style.top = `${y}px`;

        // Τυχαία καθυστέρηση για φυσική αίσθηση λάμψης
        let delay = Math.random() * 5;
        star.style.animationDelay = `${delay}s`;

        starContainer.appendChild(star);
    }
}


// Λειτουργία για τα κουμπιά
function setupButtons() {
    const buttons = document.querySelectorAll(".menu");

    buttons.forEach(button => {
        button.addEventListener("mouseenter", function () {
            button.style.color = "rgba(177, 127, 255, 1)"; // Φωτεινό μωβ
            button.style.textShadow = "0 0 10px rgba(177, 127, 255, 0.8)"; // Glow effect
        });

        button.addEventListener("mouseleave", function () {
            button.style.color = "white"; // Αρχικό χρώμα
            button.style.textShadow = "none"; // Αφαίρεση φωτισμού
        });
    });
}

function setupCardSwitching() {
    const cards = document.querySelectorAll(".card");
    const leftArrow = document.querySelector(".section-2-arrow");
    const rightArrow = document.querySelector(".icon-button-purple");
    let currentIndex = 0;

    function showCards(index) {
        cards.forEach((card, i) => {
            if (i === index || i === (index + 1) % cards.length) {
                card.classList.add("active");
                card.classList.remove("hidden");
            } else {
                card.classList.remove("active");
                card.classList.add("hidden");
            }
        });
    }

    leftArrow.addEventListener("click", function () {
        currentIndex = (currentIndex - 2 + cards.length) % cards.length; // Μετακίνηση δύο κάρτες πίσω
        showCards(currentIndex);
    });

    rightArrow.addEventListener("click", function () {
        currentIndex = (currentIndex + 2) % cards.length; // Μετακίνηση δύο κάρτες μπροστά
        showCards(currentIndex);
    });

    showCards(currentIndex); // Εμφανίζουμε τις πρώτες δύο κάρτες
}

function setupFlashlightEffect() {
    const flashlight = document.querySelector(".flashlight");
    const lightBeam = document.querySelector(".aktina");
    const letters = document.querySelectorAll(".text-ofspace span");

    let index = 0; // Τρέχον γράμμα
    let direction = 1; // Κατεύθυνση (1 = δεξιά, -1 = αριστερά)
    let speed = 0.001; // Ταχύτητα κίνησης (ρυθμιζόμενο)
    let position = 0; // Τρέχουσα θέση κίνησης

    // Ορισμός αρχικής περιστροφής
    flashlight.style.transform = "rotate(90deg)";

    function moveFlashlight() {
        const letterWidth = letters[0].offsetWidth;
        const spacing = 10; // Απόσταση μεταξύ των γραμμάτων
        const maxPosition = (letters.length - 1) * (letterWidth + spacing);

        // Ενημερώνουμε τη θέση
        position += direction * speed * maxPosition;

        // Αλλαγή κατεύθυνσης στα άκρα
        if (position >= maxPosition || position <= 0) {
            direction *= -1;
            // Περιστροφή φακού ανάλογα με την κατεύθυνση
            flashlight.style.transform = `rotate(${direction === 1 ? "90deg" : "0deg"})`;
        }

        // Μετακινούμε τον φακό και το φως
        flashlight.style.left = `${position}px`;

        // Ελέγχουμε ποια γράμματα φωτίζονται
        let letterIndex = Math.round(position / (letterWidth + spacing));
        letters.forEach((letter, i) => {
            if (i === letterIndex || i === letterIndex + 1) {
                letter.style.color = "rgba(255, 255, 255, 0.8)"; // Φωτεινό
                letter.style.textShadow = "0 0 15px rgba(255, 255, 255, 0.5)"; // Glow effect
            } else {
                letter.style.color = "rgba(255, 255, 255, 0.1)"; // Ξανά αχνό
                letter.style.textShadow = "none";
            }
        });

        // Συνεχίζουμε την κίνηση
        requestAnimationFrame(moveFlashlight);
    }

    moveFlashlight();
}