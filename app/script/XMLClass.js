class XMLClass {
    constructor(xml) {
        this.xml = undefined
        if (typeof xml == 'string') {
            this.xml = (new DOMParser()).parseFromString(xml, "text/xml")
        }
        if (typeof xml == 'object') {
            this.xml = xml
        }
    }

    _xmlToObject(xml) {
        let obj = new Object()

        if (xml.nodeType == 1) {
            if (xml.attributes.length > 0) {
                obj["@attributes"] = {}
                for (let j = 0; j < xml.attributes.length; j++) {
                    let attribute = xml.attributes.item(j)
                    obj["@attributes"][attribute.nodeName] = attribute.nodeValue
                }
            }
        } else if (xml.nodeType == 3) {
            obj = xml.nodeValue
        }

        if (xml.hasChildNodes()) {
            if (
                xml.childNodes.length == 1 &&
                (
                    xml.childNodes[0].nodeName == '#cdata-section' ||
                    xml.childNodes[0].nodeName == '#text'
                )
            ) {
                obj = xml.childNodes[0].textContent
            }
            else {
                xml.childNodes.forEach(e => {
                    if (typeof obj[e.nodeName] == 'undefined') {
                        if (e.nodeName == '#cdata-section' || e.nodeName == '#text') {
                            obj[e.nodeName] = e.textContent
                        }
                        else {
                            obj[e.nodeName] = this._xmlToObject(e)
                        }
                    }
                    else {
                        if (typeof obj[e.nodeName].push == 'undefined') {
                            let prev = obj[e.nodeName]
                            obj[e.nodeName] = new Array()
                            obj[e.nodeName].push(prev)
                        }
                        if (e.nodeName == '#cdata-section' || e.nodeName == '#text') {
                            obj[e.nodeName].push(e.textContent)
                        }
                        else {
                            obj[e.nodeName].push(this._xmlToObject(e))
                        }
                    }
                })
            }
        }
        return obj
    }

    toObject() {
        return this._xmlToObject(this.xml)
    }
}