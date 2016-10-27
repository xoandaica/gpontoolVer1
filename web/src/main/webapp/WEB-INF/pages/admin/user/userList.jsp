<%@ include file="/common/taglibs.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title><fmt:message key="management.user.title"/></title>
        <meta name="menu" content="AdminMenu" />
    </head>
    <body class="home">

        <div id='mainPanel' style="width: 100%"></div>

        <div id="tabs" class="x-hide-display">

            <div id="tabs-1" class="x-hide-display" style="height: 200px">
                <p>
                <div class="groupItem" id="">

                    <div class="itemContent" id="">
                        <div align="center" style="width: 100%">
                            <div id="searchCriteria" align="center" style="width: 100%;"></div>
                            <div id="gridView" align="center" style="width: 100%;"></div>
                        </div>
                    </div>

                </div>
                </p>
            </div>

        </div>

        <jsp:include page="jsUserStore.jsp" />
        <jsp:include page="../actionLog/jsLogStore.jsp" />
        <jsp:include page="jsUserController.jsp" />
        <jsp:include page="../actionLog/jsLogForm.jsp" />
        <jsp:include page="jsUserForm.jsp" />
        <jsp:include page="jsUserLayout.jsp" />
    </body>
</html>
