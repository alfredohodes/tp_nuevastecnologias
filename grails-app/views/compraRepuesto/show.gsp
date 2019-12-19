<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Compra Repuesto</title>
    </head>
    <body>
        <a href="#show-compraRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index">Compras Repuestos</g:link></li>
            </ul>
        </div>
        <div id="show-compraRepuesto" class="content scaffold-show" role="main">
            <h1>Compra Repuesto</h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">ID</th>
                                <td>${compraRepuesto.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Fecha</th>
                                <td>${compraRepuesto.fecha}</td>
                            </tr>
                            <tr>
                                <th scope="row">Proveedor</th>
                                <td>${compraRepuesto.proveedor}</td>
                            </tr>
                            <tr>
                                <th scope="row">Detalles</th>
                                <td>
                                    <ul class="list-inline">
                                        <g:each in="${detalleInfos.sort { it.detalleCompraRepuestoInstance.id  }}" var="detalleInfo">
                                            <li>
                                                <g:link class="show" action="show" resource="${detalleInfo.detalleCompraRepuestoInstance}">
                                                     ${detalleInfo}
                                                </g:link>
                                            </li>
                                        </g:each>
                                    </ul>
                                </td>
                            </tr>
                        </tbody>
					</table>
				</div>
			</div>

            <g:form resource="${this.compraRepuesto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.compraRepuesto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
