<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'compraRepuesto.label', default: 'Compra Repuesto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-compraRepuesto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-compraRepuesto" class="content scaffold-list" role="main">
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
								<th class="text-center">Fecha</th>
								<th class="text-center">Proveedor</th>
								<th class="text-center">Acciones</th>
							</tr>
						</thead>
						<tbody>
							<g:each in="${compraRepuestoList.sort { it.id  }}" var="compraRepuestoInstance">
								<tr>
									<td class="text-center">${compraRepuestoInstance.id}</td>
									<td class="text-center"><g:formatDate format="dd/MM/yyyy" date="${compraRepuestoInstance.fecha}"/></td>
									<td class="text-center">${compraRepuestoInstance.proveedor}</td>
                                    <td class="text-center">
                                        <g:link class="info" action="show" resource="${compraRepuestoInstance}">Ver</g:link>
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