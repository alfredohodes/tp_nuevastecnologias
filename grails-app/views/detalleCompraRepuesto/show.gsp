<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'detalleCompraRepuesto.label', default: 'Detalle Compra Repuesto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-detalleCompraRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-detalleCompraRepuesto" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            
            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
                        <tbody>
                            <tr>
                                <th scope="row">ID</th>
                                <td>${detalleCompraRepuesto.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tipo</th>
                                <td>${tipoRepuesto.nombre}</td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad Total</th>
                                <td>${cantidadTotal}</td>
                            </tr>
                            <tr>
                                <th scope="row">Precio</th>
                                <td><g:formatNumber number="${detalleCompraRepuesto.precio.monto}" type="currency" currencyCode="ARS"/></td>
                            </tr>
                            <tr>
                                <th scope="row">Disponibilidades</th>
                                <td>
                                    <ul class="list-inline">
                                        <g:each in="${detalleCompraRepuesto.repuestos.sort { it.id  }}" var="disponibilidadRepuesto">
                                            <li>
                                                <g:link class="show" action="show" resource="${disponibilidadRepuesto}">
                                                     ${disponibilidadRepuesto}
                                                </g:link>
                                            </li>
                                        </g:each>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">Compra</th>
                                <td>
                                    <g:link class="show" action="show" resource="${detalleCompraRepuesto.compra}">
                                            ${detalleCompraRepuesto.compra}
                                    </g:link>
                                </td>
                            </tr>
                        </tbody>
					</table>
				</div>
			</div>

            <g:form resource="${this.detalleCompraRepuesto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.detalleCompraRepuesto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
