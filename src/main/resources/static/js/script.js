console.log('Script loaded');
const cards = document.querySelectorAll('.memory-card');

let hasFlippedCard = false;
let lockBoard = false;
let firstCard, secondCard;
let attempts = 0;
let matchedPairs = 0;
const totalPairs = cards.length / 2;
let timerStarted = false;
const urlParams = new URLSearchParams(window.location.search);
const selectedDifficulty = urlParams.get('difficulty');

let timeLimitInSeconds;

if (selectedDifficulty === "easy") {
  timeLimitInSeconds = 120; // Set the time limit for easy difficulty
} else if (selectedDifficulty === "medium") {
  timeLimitInSeconds = 50; // Set the time limit for medium difficulty
} else if (selectedDifficulty === "hard") {
  timeLimitInSeconds = 30; // Set the time limit for hard difficulty
}
  else if(selectedDifficulty==null){
  timeLimitInSeconds=120;
}




function flipCard(element) {
  if (lockBoard) return;
  if (element.classList.contains('matched')) return;
  if (element === firstCard) return;

  element.classList.add('flip');

  if (!timerStarted) {
      startTimer(timeLimitInSeconds);
      timerStarted = true;
    }

  if (!hasFlippedCard) {
    // first click
    hasFlippedCard = true;
    firstCard = element;

    return;
  }

  // second click
  secondCard = element;

  checkForMatch();
}



//function checkForMatch() {
//  let isMatch = firstCard.dataset.framework === secondCard.dataset.framework;
//
//  isMatch ? disableCards() : unflipCards();
//}
function checkForMatch() {
  attempts++;
  document.getElementById('attempt-counter').textContent = attempts;

  const firstCardBackFace = firstCard.querySelector('.front-face');
  const secondCardBackFace = secondCard.querySelector('.front-face');

  let isMatch = firstCardBackFace.src === secondCardBackFace.src;

  if (isMatch) {
    matchedPairs++;
  }

  isMatch ? disableCards() : unflipCards();

  if (matchedPairs === totalPairs) {
    /*showWinMessage();*/
    saveAttempts(attempts);
    showWinMessage();
    clearInterval(timer);
  }
}

function showWinMessage() {
  // You can customize this message or display it in a different way, like using a modal or an alert
  alert("Congratulations, you've won! Total attempts: " + attempts);
}

async function saveAttempts(attempts) {
// Use the loggedInUsername or a default name if the user is not logged in
  const playerName = loggedInUsername || 'Anonymous';
    if(playerName==='Anonymous'){
    return;
    }
  const score = {
    game: 'pexeso',
    player: playerName,
    points: attempts,
    playedAt: new Date().toISOString(),
  };

  try {
    const response = await fetch('http://localhost:8080/api/score', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(score),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  } catch (error) {
    console.error('Failed to save attempts:', error);
  }
}



/*function disableCards() {
  firstCard.removeEventListener('click', flipCard);
  secondCard.removeEventListener('click', flipCard);

  resetBoard();
}*/
function disableCards() {
  firstCard.classList.add('matched');
  secondCard.classList.add('matched');

  firstCard.onclick = null;
  secondCard.onclick = null;

  resetBoard();
}



function unflipCards() {
  lockBoard = true;

  setTimeout(() => {
    firstCard.classList.remove('flip');
    secondCard.classList.remove('flip');

    resetBoard();
  }, 1000);
}

function resetBoard() {
  [hasFlippedCard, lockBoard] = [false, false];
  [firstCard, secondCard] = [null, null];
}

let timer;
let timeRemaining;

function startTimer(timeLimitInSeconds) {
  timeRemaining = timeLimitInSeconds;
  updateTimerDisplay();

  timer = setInterval(() => {
    timeRemaining--;

    if (timeRemaining <= 0) {
      clearInterval(timer);
      handleGameOver();
    } else {
      updateTimerDisplay();
    }
  }, 1000);
}

function updateTimerDisplay() {
  const minutes = Math.floor(timeRemaining / 60);
  const seconds = timeRemaining % 60;
  document.getElementById("time").textContent = `${minutes}:${seconds.toString().padStart(2, "0")}`;
}

function handleGameOver() {
  // ...
  alert("Time's up! Game Over.");
  resetGame();
}


function resetGame() {
  // Stop the timer
  clearInterval(timer);
  timerStarted = false;

  // Reset the game state
  attempts = 0;
  matchedPairs = 0;
  hasFlippedCard = false;
  lockBoard = false;
  firstCard = null;
  secondCard = null;
  document.getElementById('attempt-counter').textContent = attempts;

  // Reset the timer display
  document.getElementById("time").textContent = "0:00";

  // Unflip all cards
  cards.forEach(card => {
    card.classList.remove('flip');
    card.classList.remove('matched');
    card.onclick = () => flipCard(card);
  });

  // Shuffle the cards or reset the game board
  // ...
}

function startPexesoGame() {
  const difficulty = document.querySelector('input[name="difficulty"]:checked').value;
  let timeLimitInSeconds;

  switch (difficulty) {
    case 'easy':
      timeLimitInSeconds = 180; // You can set the desired time limit for easy difficulty
      break;
    case 'medium':
      timeLimitInSeconds = 120; // You can set the desired time limit for medium difficulty
      break;
    case 'hard':
      timeLimitInSeconds = 60; // You can set the desired time limit for hard difficulty
      break;
  }

  // You can also set any other game parameters based on difficulty here
}



//cards.forEach(card => card.addEventListener('click', flipCard));
cards.forEach(card => card.addEventListener('click', () => flipCard(card)));














