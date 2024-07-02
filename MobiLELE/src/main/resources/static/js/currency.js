let currencyDropdown = document.getElementById('currency');
currencyDropdown.addEventListener('change', currencyChange);

function currencyChange() {
    let selectedCurrency = currencyDropdown.value;
    let amountInBGN = document.getElementById('priceInBGN').value;
    let priceSpan = document.getElementById('price');

    fetch('/api/convert?' + new URLSearchParams(
        {
            from: 'BGN',
            to: selectedCurrency,
            amount: amountInBGN
        }
    ))
        .then(res => res.json())
        .then(data => {
            priceSpan.textContent = data.result;
        })
        .catch(err => console.log('An error occurred: ' + err));
}
