<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'disponibilidadRepuesto.label', default: 'DisponibilidadRepuesto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-disponibilidadRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-disponibilidadRepuesto" class="content scaffold-show" role="main">
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
                                <td>${disponibilidadRepuesto.id}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tipo</th>
                                <td>${disponibilidadRepuesto.tipo.nombre}</td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad Total</th>
                                <td>${disponibilidadRepuesto.cantidad}</td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad Reservada</th>
                                <td>${disponibilidadRepuesto.cantidadReservada}</td>
                            </tr>
                            <tr>
                                <th scope="row">Cantidad Disponible</th>
                                <td>${disponibilidadRepuesto.cantidad - disponibilidadRepuesto.cantidadReservada}</td>
                            </tr>
                            <tr>
                                <th scope="row">Numero de serie</th>
                                <td>${disponibilidadRepuesto.numeroDeSerie}</td>
                            </tr>
                            <tr>
                                <th scope="row">Lote</th>
                                <td>${disponibilidadRepuesto.lote}</td>
                            </tr>
                            <tr>
                                <th scope="row">Fecha de vencimiento</th>
								<td class="text-center"><g:formatDate format="dd/MM/yyyy" date="${disponibilidadRepuesto.vencimiento}"/></td>
                            </tr>
                            <tr>
                                <th scope="row">Ubicacion</th>
                                <td>${disponibilidadRepuesto.ubicacion}</td>
                            </tr>
                        </tbody>
					</table>
				</div>
			</div>

            <g:form resource="${this.disponibilidadRepuesto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.disponibilidadRepuesto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
