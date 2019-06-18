document.querySelector('form').addEventListener('submit', function (e) {
    e.preventDefault()
    
    let config = new configClass()
    let request = new FetchResource('json', 'json', {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': localStorage.getItem('token')
    })

    let nome = document.getElementsByName('nome')[0].value
    let descricao = document.getElementsByName('descricao')[0].value

    let obj = {
        nome: nome,
        descricao: descricao
    }
    request.POST(config.getUrl() + 'projeto', obj).then(e => {
        console.log(e)
        // window.location.href = '../index.html'
    }).catch(e => {
        console.log(e)
    })
})