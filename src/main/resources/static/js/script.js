console.log('Script loaded');
const cards = document.querySelectorAll('.memory-card');

let hasFlippedCard = false;
let lockBoard = false;
let firstCard, secondCard;
let attempts = 0;
let matchedPairs = 0;
const totalPairs = cards.length / 2;


function flipCard(element) {
  if (lockBoard) return;
  if (element.classList.contains('matched')) return;
  if (element === firstCard) return;

  element.classList.add('flip');

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
    showWinMessage();
    saveAttempts(attempts);
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
  }, 1500);
}

function resetBoard() {
  [hasFlippedCard, lockBoard] = [false, false];
  [firstCard, secondCard] = [null, null];
}

/*async function submitComment(commentText) {
  const playerName = loggedInUsername || 'Anonymous';
  if (playerName === 'Anonymous') {
    return;
  }

  const currentTimestamp = new Date();

  const comment = {
    player: playerName,
    game: 'pexeso',
    comment: commentText,
    commented_at: currentTimestamp.toISOString()
  };

  try {
    const response = await fetch('http://localhost:8080/api/comment', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(comment),
    });

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
  } catch (error) {
    console.error('Failed to submit comment:', error);
  }
}

document.getElementById('comment-form').addEventListener('submit', async (event) => {
  event.preventDefault();
  const commentText = document.getElementById('comment').value;
  await submitComment(commentText);
});*/




//cards.forEach(card => card.addEventListener('click', flipCard));
cards.forEach(card => card.addEventListener('click', () => flipCard(card)));














