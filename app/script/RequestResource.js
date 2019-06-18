class RequestResource extends RequestException {
    constructor(
        requestEncode = 'json',
        responseDecode = 'json',
        headerFieldName = 'Content-Type',
        headerField = 'application/json;charset=UTF-8'
    ) {
        super()
        this.requestEncode = undefined
        this.responseDecode = undefined
        this.headerFieldName = undefined
        this.headerField = undefined

        if (typeof requestEncode == 'string') {
            this.requestEncode = requestEncode.trim().toLowerCase()
        }
        if (typeof responseDecode == 'string') {
            this.responseDecode = responseDecode.trim().toLowerCase()
        }
        if (typeof headerFieldName == 'string') {
            this.headerFieldName = headerFieldName
        }
        if (typeof headerField == 'string') {
            this.headerField = headerField
        }

        this.responseText = undefined
        this.responseType = undefined
        this.responseURL = undefined
        this.responseXML = undefined
        this.status = undefined
        this.statusText = undefined
    }

    GET(url) {
        return this._request('GET', url)
    }

    POST(url, obj) {
        return this._request('POST', url, obj)
    }

    PUT(url, obj) {
        return this._request('PUT', url, obj)
    }

    DELETE(url, obj) {
        return this._request('DELETE', url, obj)
    }

    getResponseText() {
        return this.responseText
    }

    getResponseType() {
        return this.responseType
    }

    getResponseURL() {
        return this.responseURL
    }

    getResponseXML() {
        return this.responseXML
    }

    getStatus() {
        return this.status
    }

    getStatusText() {
        return this.statusText
    }

    _request(method, url, obj = undefined) {
        return new Promise((resolve, reject) => {
            if (typeof method == 'string') {
                let json = this._requestEncodeObj(obj)
                console.log('send:' + json)
                if (url) {
                    if (typeof url == 'string') {
                        if (this.headerFieldName && this.headerField) {
                            let request = new XMLHttpRequest()
                            request.open(method, url, true)
                            request.setRequestHeader(this.headerFieldName, this.headerField)
                            request.send(json)
                            request.onreadystatechange = () => {
                                if (request.readyState == 4) { //DONE
                                    this.responseText = request.responseText
                                    this.responseType = request.responseType
                                    this.responseURL = request.responseURL
                                    this.responseXML = request.responseXML
                                    this.status = request.status
                                    this.statusText = request.statusText
                                    console.log(request)
                                    if (this.isResponseStatus(request.status)) {
                                        resolve(this._decodeResponseText(request.responseText))
                                    }
                                    else {
                                        reject()
                                    }
                                }
                            }
                        }
                        else {
                            this.invalidHeader()
                            reject()
                        }
                    }
                    else {
                        this.invalidUrl()
                        reject()
                    }
                }
                else {
                    this.invalidUrl()
                    reject()
                }
            }
            else {
                this.invalidMethod()
                reject()
            }
        })
    }

    _requestEncodeObj(obj) {
        if (obj) {
            if (typeof obj == 'object') {
                if (this.requestEncode = 'json') {
                    return JSON.stringify(obj)
                }
                else {
                    return obj
                }
            }
            else {
                this.invalidObject()
            }
        }
        return null
    }

    _decodeResponseText(t) {
        if (t != '') {
            if (this.responseDecode == 'json') {
                return JSON.parse(t)
            }
            else if (this.responseDecode == 'xml') {
                return new XMLClass(t).toObject()
            }
        }
        return new Object()
    }
}