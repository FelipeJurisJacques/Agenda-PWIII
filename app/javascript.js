document.querySelector('form').addEventListener('submit', function (e) {
    e.preventDefault()

    let config = new configClass()

    const email = document.getElementsByName('email')[0].value
    const senha = document.getElementsByName('senha')[0].value

    let obj = {
        email: email,
        senha: senha
    }

    let request = new FetchResource('json', 'header')
    request.POST(config.getUrl() + 'login', obj).then(e => {
        console.log(e)
        if(e.authorization){
            localStorage.setItem('token', e.authorization)
            window.location.href = 'projeto/index.html'
        }
    }).catch(e => {
        console.log(e)
        alert(e)
    })
})