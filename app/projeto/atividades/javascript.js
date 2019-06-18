const url = new RequestUrlResource()
if (url.getItens().id) {
    const id = url.getItens().id

    document.querySelector('#new').setAttribute('onclick', "window.location.href='novo/index.html?id=" + id + "'")

    const config = new configClass()
    const request = new FetchResource('json', 'json', {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': localStorage.getItem('token')
    })

    request.GET(config.getUrl() + 'atividade').then(e => {
        e.forEach(element => {
            showAtividade(element)
        })
    }).catch(e => {
        console.log(e)
    })

    function showAtividade(obj) {
        console.log(obj)

        const atividade = document.createElement('div')
        atividade.setAttribute('class', 'card')

        const title = document.createElement('div')
        title.setAttribute('class', 'cardTitle')
        const name = document.createElement('h2')
        name.innerHTML = obj.nome
        title.appendChild(name)

        const content = document.createElement('div')
        content.setAttribute('class', 'cardContent')

        const descrissao = document.createElement('label')
        descrissao.innerHTML = obj.descrissao + '<br><br>'

        const inicio = document.createElement('label')
        if (obj.inicio == null) {
            if (obj.inicioEstimado == null) {
                inicio.innerHTML = '<b>Inicia em:</b> sem previsão <br>'
            }
            else {
                date = new Date(obj.inicioEstimado)
                inicio.innerHTML = '<b>Inicia em: </b>' + date.toLocaleDateString() + '<br>'
            }
        }
        else {
            date = new Date(obj.inicio)
            inicio.innerHTML = '<b>Iniciou: </b>' + date.toLocaleDateString()
            if (obj.inicioEstimado == null) {
                inicio.innerHTML += '<br>'
            }
            else {
                date = new Date(obj.inicioEstimado)
                inicio.innerHTML += '. Estimado para: ' + date.toLocaleDateString() + '<br>'
            }
        }

        const termino = document.createElement('label')
        if (obj.termino == null) {
            if (obj.terminoEstimado == null) {
                termino.innerHTML = '<b>Finaliza em:</b> sem previsão <br>'
            }
            else {
                date = new Date(obj.terminoEstimado)
                termino.innerHTML = '<b>Finaliza em: </b>' + date.toLocaleDateString() + '<br>'
            }
        }
        else {
            date = new Date(obj.termino)
            termino.innerHTML = '<b>Finalizou: </b>' + date.toLocaleDateString()
            if (obj.terminoEstimado == null) {
                termino.innerHTML += '<br>'
            }
            else {
                date = new Date(obj.terminoEstimado)
                termino.innerHTML += '. Estimado para: ' + date.toLocaleDateString() + '<br>'
            }
        }

        const criado = document.createElement('label')
        date = new Date(obj.criado)
        criado.innerHTML = '<sub><b>Criado: </b>' + date.toLocaleDateString() + '<sub>'

        content.appendChild(descrissao)
        content.appendChild(inicio)
        content.appendChild(termino)
        content.appendChild(criado)

        atividade.append(title)
        atividade.append(content)

        const deletar = document.createElement('button')
        deletar.setAttribute('class', 'buttonBlue')
        deletar.setAttribute('onclick', 'DeletarAtividade(' + obj.id + ')')
        deletar.innerHTML = 'remover'

        const footer = document.createElement('div')
        footer.setAttribute('class', 'cardFooter')
        footer.appendChild(deletar)
        atividade.append(footer)

        if (obj.estado < 3 || obj.estado == 4) {
            const estado = document.createElement('select')
            estado.setAttribute('onchange', 'AtualizarAtividade(this.value, ' + obj.id + ')')
            if (obj.estado == 1) {
                const op1 = document.createElement('option')
                op1.innerHTML = 'Pendente'
                op1.value = "1"
                estado.appendChild(op1)
            }
            const op2 = document.createElement('option')
            op2.innerHTML = 'Executando'
            op2.value = "2"
            estado.appendChild(op2)
            if (obj.estado > 1) {
                const op3 = document.createElement('option')
                op3.innerHTML = 'Finalizado'
                op3.value = "3"
                estado.appendChild(op3)
            }
            const op4 = document.createElement('option')
            op4.innerHTML = 'Atrazado'
            op4.value = "4"
            estado.appendChild(op4)
            estado.value = obj.estado

            footer.appendChild(estado)
        }

        if (obj.estado == 2) {
            document.querySelector('body main .grupo2').appendChild(atividade)
        }
        else if (obj.estado == 3) {
            document.querySelector('body main .grupo3').appendChild(atividade)
        }
        else {
            document.querySelector('body main .grupo1').appendChild(atividade)
        }
    }

    function AtualizarAtividade(estado, id) {
        if (confirm("Deseja alterar o estado dessa atividade?")) {
            const obj = {
                estado: estado
            }
            
            request.PUT(config.getUrl() + 'atividade/' + id, obj).then(e => {
                console.log(e)
                window.location.reload()
            }).catch(e => {
                console.log(e)
                alert(e)
            })
        }
    }

    function DeletarAtividade(id) {
        if (confirm("Deseja remover essa atividade?")) {
            request.DELETE(config.getUrl() + 'atividade/' + id).then(e => {
                console.log(e)
                window.location.reload()
            }).catch(e => {
                console.log(e)
                alert(e)
            })
        }
    }
}