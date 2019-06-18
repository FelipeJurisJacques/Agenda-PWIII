const url = new RequestUrlResource()
if (url.getItens().id) {
    const id = url.getItens().id

    document.querySelector('form').addEventListener('submit', function (e) {
        e.preventDefault()

        const config = new configClass()
        const request = new FetchResource('json', false, {
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': localStorage.getItem('token')
        })

        const nome = document.getElementsByName('nome')[0].value
        const descrissao = document.getElementsByName('descrissao')[0].value
        const inicioEstimado = document.getElementsByName('inicioEstimado')[0].value
        const terminoEstimado = document.getElementsByName('terminoEstimado')[0].value

        const obj = {
            nome: nome,
            descrissao: descrissao,
            inicioEstimado: inicioEstimado,
            terminoEstimado: terminoEstimado,
            projeto: id
        }

        request.POST(config.getUrl() + 'atividade', obj).then(e => {
            console.log(e)
            window.location.href = '../index.html?id=' + id
        }).catch(e => {
            console.log(e)
            alert(e)
        })
    })
}