const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

document.querySelectorAll('.clickable-completed').forEach(td => {
    td.addEventListener('click', function (event) {
        // Чтобы не сработал двойной обработчик, если нажали по самому чекбоксу, разрешаем ему событие отдельно
        if (event.target.tagName.toLowerCase() === 'input') {
            // Если клик именно на чекбоксе — делаем обычную обработку (чекбокс сам переключится)
            // Можно либо вернуть, либо обработать так же. Для простоты вернём:
            return;
        }

        const checkbox = this.querySelector('input[type="checkbox"]');
        if (!checkbox) return;

        // Переключаем чекбокс вручную
        checkbox.checked = !checkbox.checked;

        const taskId = this.dataset.id;
        const completed = checkbox.checked;

        fetch(`/tasks/${taskId}/completed`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                [csrfHeader]: csrfToken
            },
            body: JSON.stringify({ completed: completed })
        })
            .then(response => {
                if (!response.ok) throw new Error('Ошибка обновления');
                return response.json();
            })
            .then(data => {
                console.log('Статус задачи обновлен', data);
            })
            .catch(error => {
                alert('Не удалось обновить статус задачи');
                checkbox.checked = !completed; // Отменяем чекбокс при ошибке
            });
    });
});
