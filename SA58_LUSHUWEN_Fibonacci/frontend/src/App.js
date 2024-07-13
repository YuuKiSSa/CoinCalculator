import React, { useState } from 'react';
import axios from 'axios';
import './App.css'

function CoinCalculator() {
    const [amount, setAmount] = useState(0);
    const [denominations, setDenominations] = useState('');
    const [result, setResult] = useState([]);
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://54.255.207.167:8080/calculate-coins', {
                amount: amount,
                denominations: denominations.split(',').map(Number)
            });
            setResult(response.data);
            setError("");
            
        } catch (error) {
          if (error.response) {
            setError(error.response.data); // Display server validation errors or other messages
        } else if (error.request) {
            setError('No response received from server');
        } else {
            setError(error.message); // Display generic error message
        }
        setResult(''); // Clear result if there was an error
        }
    };

    return (
      <div className="coin-calculator">
      <h1>Coin Calculator</h1>
      <form onSubmit={handleSubmit}>
          <label>
              Amount:
              <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} />
          </label>
          <br />
          <label>
              Denominations (comma-separated):
              <input type="text" value={denominations} onChange={(e) => setDenominations(e.target.value)} />
          </label>
          <br />
          <button type="submit">Calculate Coins</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
            {result && (
                <div>
                    <h2>Result:</h2>
                    <ul>
                        {result.map((coin, index) => (
                            <li key={index}>{coin}</li>
                        ))}
                    </ul>
                </div>
            )}
      </div>
    );
}

export default CoinCalculator;
