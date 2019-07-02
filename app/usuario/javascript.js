const config = new configClass()
const request = new FetchResource('json', false, {
    'Content-Type': 'application/json;charset=UTF-8',
    'Authorization': localStorage.getItem('token')
})

request.GET(config.getUrl() + 'usuario').then(e => {
    console.log('resposta: ')
    console.log(e)
    if (e.nome) {
        document.querySelector('#user').innerHTML = e.nome
    }
    if(e.email){
        document.querySelector('#email').innerHTML = e.email
    }
}).catch(e => {
    console.log(e)
})

let senha1 = document.getElementsByName('senha1')[0]
let senha2 = document.getElementsByName('senha2')[0]

senha2.addEventListener('change', function () {
    if (this.value != senha1.value) {
        this.value = ''
    }
})

document.querySelector('form').addEventListener('submit', function (e) {
    e.preventDefault()

    let obj = {
        senha: document.getElementsByName('senha2')[0].value
    }
    
    request.POST(config.getUrl() + 'usuario/PUT/', obj).then(e => {
        console.log(e)
        window.location.href = '../projeto/index.html'
    }).catch(e => {
        console.log(e)
        alert(e)
    })
})
