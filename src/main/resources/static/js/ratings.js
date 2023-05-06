// Rating form submission
const ratingForm = document.getElementById('rating-form');
if (ratingForm) {
  ratingForm.addEventListener('submit', async (event) => {
    event.preventDefault();
    const ratingValue = document.getElementById('rating').value;
    const playerName = loggedInUsername || 'Anonymous';
    if (playerName === 'Anonymous') {
      return;
    }

    const rating = {
      player: playerName,
      game: 'pexeso',
      rating: parseInt(ratingValue),
      ratedAt: new Date().toISOString(),
    };

    // Send the rating data to the server
    try {
      const response = await fetch('/api/rating', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(rating),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      alert('Thank you for your rating!');
    } catch (error) {
      console.error('Error:', error);
      alert('Error: Could not submit your rating.');
    }
  });
}


