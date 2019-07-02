const config = new configClass()
const request = new FetchResource('json', 'json', {
    'Content-Type': 'application/json;charset=UTF-8',
    'Authorization': localStorage.getItem('token')
})

request.GET(config.getUrl() + 'usuario').then(e => {
    console.log('resposta: ')
    console.log(e)
    if (e.nome) {
        let user = document.createElement('a')
        user.setAttribute('href', '../usuario/index.html')
        user.innerHTML = e.nome
        document.querySelector('#user').appendChild(user)
    }
    if (e.projetos) {
        if (Array.isArray(e.projetos)) {
            e.projetos.forEach(element => {
                showProjeto(element)
            })
        }
    }
}).catch(e => {
    console.log(e)
})

// request.GET(config.getUrl() + 'projeto').then(e => {
//     console.log('resposta: ')
//     console.log(e)
//     if (Array.isArray(e)) {
//         e.forEach(element => {
//             showProjeto(element)
//         })
//     }
// }).catch(e => {
//     console.log(e)
// })

function showProjeto(obj) {
    const atividade = document.createElement('div')
    atividade.setAttribute('class', 'card')

    const title = document.createElement('div')
    title.setAttribute('class', 'cardTitle')

    const name = document.createElement('a')
    name.setAttribute('href', 'atividades/index.html?id=' + obj.id)
    name.innerHTML = obj.nome

    title.appendChild(name)

    const content = document.createElement('div')
    content.setAttribute('class', 'cardContent')

    const descricao = document.createElement('label')
    descricao.innerHTML = obj.descricao + '<br><br>'
    content.appendChild(descricao)

    if (obj.usuarios) {
        if (Array.isArray(obj.usuarios)) {
            obj.usuarios.forEach(e => {
                if (e.email && e.nome) {
                    const grupo = document.createElement('label')
                    grupo.innerHTML = 'Compartilhado com ' + e.nome + ': ' + e.email + '<br>'
                    content.appendChild(grupo)
                }
            })
        }
    }

    atividade.append(title)
    atividade.append(content)

    const footer = document.createElement('div')
    footer.setAttribute('class', 'cardFooter')

    const deletar = document.createElement('button')
    deletar.setAttribute('class', 'buttonBlue')
    deletar.setAttribute('onclick', 'DeletarProjeto(' + obj.id + ')')
    deletar.innerHTML = 'remover'
    footer.appendChild(deletar)

    const compartilhar = document.createElement('input')
    compartilhar.setAttribute('name', 'email')
    compartilhar.setAttribute('placeholder', 'Compartilhar com...')
    compartilhar.setAttribute('title', 'Insira o e-mail para compartilhar esse projeto')
    compartilhar.setAttribute('class', 'inputField')
    compartilhar.setAttribute('onchange', 'AtualizarProjeto(this,' + obj.id + ')')
    footer.appendChild(compartilhar)

    atividade.append(footer)

    document.querySelector('body main').appendChild(atividade)
}

function DeletarProjeto(id) {
    if (confirm("Deseja remover essa projeto?")) {
        request.setOutput(false)
        
        //request.DELETE(config.getUrl() + 'projeto/' + id).then(e => {
        request.POST(config.getUrl() + 'projeto/DELETE/' + id).then(e => {
            console.log(e)
            // window.location.reload()
        }).catch(e => {
            console.log(e)
            alert(e)
        })
    }
}

function AtualizarProjeto(e, id) {
    if (e.value) {
        let value = e.value
        if (typeof value == 'string') {
            let emails = value.split(',').map(i => i.trim())
            let obj = {
                email: emails
            }
            console.log(JSON.stringify(obj))
            if (confirm('Deseja compartilhar esse projeto com os usuários: ' + emails)) {
                request.setOutput(false)

                // request.PUT(config.getUrl() + 'projeto/' + id, obj).then(e => {
                request.POST(config.getUrl() + 'projeto/PUT/' + id, obj).then(e => {
                    console.log(e)
                    // window.location.reload()
                }).catch(e => {
                    console.log(e)
                    alert(e)
                })
            }
        }
    }
}