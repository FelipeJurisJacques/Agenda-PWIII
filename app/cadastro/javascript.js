let senha1 = document.getElementsByName('senha1')[0]
let senha2 = document.getElementsByName('senha2')[0]

senha2.addEventListener('change', function () {
    if (this.value != senha1.value) {
        this.value = ''
    }
})

document.querySelector('form').addEventListener('submit', function (e) {
    e.preventDefault()

    let config = new configClass()

    let nome = document.getElementsByName('nome')[0].value
    let email = document.getElementsByName('email')[0].value
    let senha = document.getElementsByName('senha2')[0].value

    let obj = {
        nome: nome,
        email: email,
        senha: senha
    }

    let request = new FetchResource('json', false)
    request.POST(config.getUrl() + 'usuario', obj).then(e => {
        console.log(e)
        window.location.href = '../index.html'
    }).catch(e => {
        console.log(e)
        alert(e)
    })
})