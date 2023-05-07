async function submitComment(commentText) {
  const playerName = loggedInUsername || 'Anonymous';
  if (playerName === 'Anonymous') {
    return;
  }

  const comment = {
    player: playerName,
    game: 'pexeso',
    comment: commentText,
    commentedAt: new Date().toISOString(),
  };
console.log('Submitting comment:', comment);
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
    alert('Thank you for your comment!');
  } catch (error) {
    console.error('Failed to submit comment:', error);
  }
}


document.getElementById('comment-form').addEventListener('submit', async (event) => {
  event.preventDefault();
  const commentText = document.getElementById('comment').value;
  await submitComment(commentText);
});