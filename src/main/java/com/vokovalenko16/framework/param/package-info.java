/**
 * Package of RESTFul parameters.
 * <p>
 *   Every param bean must extends
 *   {@link com.vokovalenko16.framework.param.BaseParam}
 *
 *   Now you can use
 *   `isSignValid(String publicKey)` function to validate the signature is correct or not.
 *
 *   Use `sign(String privateKey)` function to sign.
 *
 *   Use `getIncorrectParams()` to get null fields.
 * </p>
 *
 * @author <a href="http://github.com/vokovalenko16">Vladymyr Kovalenko</a>
 * @date 9/22/15
 * @since JDK1.8
 */
package com.vokovalenko16.framework.param;