<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'ordenDeTrabajo.label', default: 'OrdenDeTrabajo')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-ordenDeTrabajo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-ordenDeTrabajo" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="row">
				<div class="col-md-8 col-md-offset-2">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th class="text-center">ID</th>
								<th class="text-center">Estado</th>
								<th class="text-center">Requerimientos</th>
								<th class="text-center">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${ordenDeTrabajoList.sort { it.id  }}" var="ordenDeTrabajoInstance">
								<tr>
									<td class="text-center">${ordenDeTrabajoInstance.id}</td>
									<td class="text-center">${ordenDeTrabajoInstance.estado}</td>
                                    <td class="text-center">
                                        <ul>
                                            <g:each in="${ordenDeTrabajoInstance.requerimientoRepuestos.sort { it.id  }}" var="requerimientoRepuestoInstance">
                                                <li>${requerimientoRepuestoInstance}</li>
                                            </g:each>
                                        </ul>
                                    </td>
                                    <td class="text-center">
                                        <g:link class="info" action="show" resource="${ordenDeTrabajoInstance}">Ver</g:link>
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
				</div>
			</div>
        </div>
    </body>
</html>