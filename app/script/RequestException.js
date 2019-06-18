class RequestException {
    constructor() { }

    isResponseStatus(status) {
        if (status == 200) { //OK
            this.show('Ok')
            return true
        }
        if (status == 201) { //OK
            this.show('Criado')
            return true
        }
        if (status == 202) { //OK
            this.show('Aceito')
            return true
        }
        if (status == 203) { //FUNCIONOU MAS O RETONO NAO DEVE SER DEVOLVIDO
            this.show('Não autorizado')
            return true
        }
        if (status == 204) { //OK
            this.show('Nenhum conteúdo')
            return true
        }
        else if (status == 400) { //INVALID_ARGUMENT || FAILED_PRECONDITION || OUT_OF_RANGE
            this.show(new Error('Algum argumento inválido foi especificado'))
        }
        else if (status == 401) { //UNAUTHENTICATED
            this.show(new Error('Credenciais de autenticação inválidas'))
        }
        else if (status == 403) { //PERMISSION_DENIED
            this.show(new Error('Acesso proíbido'))
        }
        else if (status == 409) { //ABORTED || ALREADY_EXISTS
            this.show(new Error('Requisição abortada'))
        }
        else if (status == 499) { //CANCELLED
            this.show(new Error('Cancelado pelo cliente'))
        }
        else if (status == 500) { //INTERNAL_SERVER_ERROR || DATA_LOSS || UNKNOWN
            this.show(new Error('Erro dentro do servidor'))
        }
        else {
            this.show(new Error('Codigo de erro ' + status))
        }
        return false
    }

    invalidObject() {
        this.show(new Error('Objeto inválido'))
    }

    invalidUrl() {
        this.show(new Error('Url inválida'))
    }

    invalidHeader() {
        this.show(new Error('Cabeçalho inválido'))
    }

    invalidMethod() {
        this.show(new Error('Método inválido'))
    }

    show(mensage) {
        console.log('Requisição: ' + mensage)
    }
}