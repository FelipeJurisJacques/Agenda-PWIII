function UpdateToken() {
    const config = new configClass()
    const request = new FetchResource('json', 'header', {
        'Content-Type': 'application/json;charset=UTF-8',
        'Authorization': localStorage.getItem('token')
    })
    
    request.POST(config.getUrl() + 'auth/refreshtoken').then(e => {
        if(e.authorization){
            localStorage.setItem('token', e.authorization)
            console.log('Token atualizado: ' + e.authorization)
        }
    }).catch(e => {
        console.log(e)
    })
}

UpdateToken()