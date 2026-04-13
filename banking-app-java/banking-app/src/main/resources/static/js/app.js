const customerForm = document.getElementById('customerForm');
const accountForm = document.getElementById('accountForm');
const depositForm = document.getElementById('depositForm');
const withdrawForm = document.getElementById('withdrawForm');

customerForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    fullName: document.getElementById('fullName').value,
    email: document.getElementById('email').value,
    phone: document.getElementById('phone').value
  };
  await fetch('/api/customers', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  location.reload();
});

accountForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const payload = {
    customerId: Number(document.getElementById('customerId').value),
    accountType: document.getElementById('accountType').value,
    initialDeposit: Number(document.getElementById('initialDeposit').value)
  };
  await fetch('/api/accounts', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  location.reload();
});

depositForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const accountId = document.getElementById('depositAccountId').value;
  const payload = {
    amount: Number(document.getElementById('depositAmount').value),
    description: document.getElementById('depositDescription').value
  };
  const response = await fetch(`/api/accounts/${accountId}/deposit`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  const data = await response.json();
  alert(response.ok ? `Updated balance: ${data.balance}` : data.error);
  location.reload();
});

withdrawForm.addEventListener('submit', async (e) => {
  e.preventDefault();
  const accountId = document.getElementById('withdrawAccountId').value;
  const payload = {
    amount: Number(document.getElementById('withdrawAmount').value),
    description: document.getElementById('withdrawDescription').value
  };
  const response = await fetch(`/api/accounts/${accountId}/withdraw`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  const data = await response.json();
  alert(response.ok ? `Updated balance: ${data.balance}` : data.error);
  location.reload();
});

async function loadTransactions() {
  const accountId = document.getElementById('transactionAccountId').value;
  const response = await fetch(`/api/accounts/${accountId}/transactions`);
  const data = await response.json();
  const tbody = document.getElementById('transactionTableBody');
  tbody.innerHTML = '';
  data.forEach(tx => {
    tbody.innerHTML += `
      <tr>
        <td>${tx.id}</td>
        <td>${tx.transactionType}</td>
        <td>${tx.amount}</td>
        <td>${tx.description}</td>
        <td>${tx.transactionTime.replace('T', ' ')}</td>
      </tr>`;
  });
}
