package com.ademir.moview.data.remote.payloads

import com.ademir.moview.model.User

/**
 * Created by ademir on 22/03/17.
 */

class UserPayload {

    var user: User? = null
        get() {
            field!!.token = "Token " + token
            return field
        }

    var token: String? = null


}
