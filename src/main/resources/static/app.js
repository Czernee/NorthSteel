document.getElementById('addSupplierForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const [name, contact] = e.target.elements;
    await fetch('http://localhost:8080/api/suppliers', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: name.value, contactInfo: contact.value })
    });
    alert('Поставщик добавлен!');
    e.target.reset();
});

document.getElementById('addProductForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const [name, type, price] = e.target.elements;
    await fetch('http://localhost:8080/api/products', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            name: name.value,
            type: type.value,
            pricePerKg: parseFloat(price.value)
        })
    });
    alert('Продукт добавлен!');
    e.target.reset();
});

document.getElementById('addDeliveryForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const [supplierId, date, ...items] = e.target.elements;
    const deliveryData = {
        supplierId: parseInt(supplierId.value),
        deliveryDate: date.value,
        items: []
    };

    for (let i = 0; i < items.length; i += 2) {
        deliveryData.items.push({
            productId: parseInt(items[i].value),
            weight: parseFloat(items[i + 1].value)
        });
    }

    await fetch('http://localhost:8080/api/deliveries', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(deliveryData)
    });
    alert('Поставка добавлена!');
    e.target.reset();
});

async function loadReport() {
    const startDate = document.getElementById('startDate').value;
    const endDate = document.getElementById('endDate').value;
    const response = await fetch(`http://localhost:8080/api/reports?startDate=${startDate}&endDate=${endDate}`);
    const data = await response.json();

    const tbody = document.querySelector('#reportTable tbody');
    tbody.innerHTML = '';
    data.forEach(item => {
        tbody.innerHTML += `
            <tr>
                <td>${item.supplierName}</td>
                <td>${item.productName}</td>
                <td>${item.totalWeight}</td>
                <td>${item.totalCost}</td>
            </tr>
        `;
    });
}

function addDeliveryItem() {
    const itemsDiv = document.getElementById('deliveryItems');
    const newItem = document.createElement('div');
    newItem.className = 'item';
    newItem.innerHTML = `
        <input type="number" placeholder="ID продукта" required>
        <input type="number" placeholder="Вес (кг)" required>
    `;
    itemsDiv.appendChild(newItem);
}