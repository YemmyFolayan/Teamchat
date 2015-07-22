<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <title>Teamchat Integration Console</title>
  <!-- favicon -->
  <link rel="shortcut icon" href="https://enterprise.teamchat.com/webjim-echat/static/img/favicon.png" type="image/x-icon">
  <!-- Theme colour for android lollipop -->
  <meta name="theme-color" content="#159ceb">
  <!-- 1. Load webcomponents-lite.min.js for polyfill support. -->
  <script src="bower_components/webcomponentsjs/webcomponents-lite.min.js">
  </script>
  <!-- 2. Use an HTML Import to bring in some elements. -->
  <link rel="import" href="bower_components/iron-icon/iron-icon.html">
  <link rel="import" href="bower_components/iron-icons/iron-icons.html">
  <link rel="import" href="bower_components/iron-form/iron-form.html">
  <link rel="import" href="bower_components/font-roboto/roboto.html">
  <link rel="import" href="bower_components/iron-media-query/iron-media-query.html">
  <link rel="import" href="bower_components/iron-collapse/iron-collapse.html">
  <link rel="import" href="bower_components/paper-styles/paper-styles.html">
  <link rel="import" href="bower_components/paper-button/paper-button.html">
  <link rel="import" href="bower_components/paper-dialog/paper-dialog.html">
  <link rel="import" href="bower_components/paper-dialog-scrollable/paper-dialog-scrollable.html">
  <link rel="import" href="bower_components/paper-fab/paper-fab.html">
  <link rel="import" href="bower_components/paper-item/paper-icon-item.html">
  <link rel="import" href="bower_components/paper-item/paper-item.html">
  <link rel="import" href="bower_components/paper-item/paper-item-body.html">
  <link rel="import" href="bower_components/paper-input/paper-input.html">
  <link rel="import" href="bower_components/paper-input/paper-input-char-counter.html">
  <link rel="import" href="bower_components/paper-input/paper-textarea.html">
  <link rel="import" href="bower_components/paper-menu/paper-menu.html">
  <link rel="import" href="bower_components/paper-item/paper-item.html">
  <link rel="import" href="bower_components/paper-item/paper-icon-item.html">
  <link rel="import" href="bower_components/paper-ripple/paper-ripple.html">
  <link rel="import" href="bower_components/paper-spinner/paper-spinner.html">
  <link rel="import" href="bower_components/paper-toast/paper-toast.html">
  <link rel="import" href="bower_components/paper-toolbar/paper-toolbar.html">
  <link rel="import" href="bower_components/paper-tabs/paper-tabs.html">
  <link rel="import" href="bower_components/paper-tabs/paper-tab.html">
  <link rel="import" href="bower_components/paper-icon-button/paper-icon-button.html">
  <link rel="import" href="bower_components/gold-email-input/gold-email-input.html">
  <link rel="import" href="bower_components/neon-animation/neon-animated-pages.html">
  <link rel="import" href="bower_components/neon-animation/neon-animatable.html">
  <link rel="import" href="bower_components/neon-animation/neon-animations.html">
  <!-- global custom style sheet (applies to outside polymer) -->
  <link rel="stylesheet" href="css/dashboard-styles-global.css">
  <!-- custom style -->
  <link rel="import" href="css/dashboard-styles.html">
  <!-- Content rendering scripts -->
  <script src="scripts/jquery-2.1.3.min.js"></script>
  <!--[if lte IE 8]>
      <script src="scripts/jquery-1.11.3.min.js"></script>
  <![endif]-->
  <script src="scripts/Constants.js"></script>
  <script src="scripts/mustache.js"></script>
  <script src="scripts/helpers.js"></script>
  <script src="scripts/admin.js"></script>
  <script src="scripts/util.js"></script>
  <script src="scripts/templates.js"></script>
  <script src="scripts/mrJsonTableConfig.js"></script>
  <script src="scripts/registerFunctions.js"></script>
  <script src="scripts/mrjsontable.js"></script>
  <script>
    $(document).ready(
      function() {
        var url = location.search;
        if (url.indexOf('errMsg=') > 0) {
          var errMsg = url.substring(url.indexOf('errMsg='),
            url.length).split('=')[1];
          console.log(errMsg);
          errMsg = unescape(errMsg);
          $('#errMsg').text(errMsg).show();
        }
      });

    function showPopUp(id) {
      $('#main-body').hide();
      $(id).css("display", "block");
    }

    function hidePopup(id) {
      $(id).css("display", "none");
      $('#main-body').show();
    }

    var renderRegisteredUtils = function() {
      var client = new Object();
      client.adminEmail = '${sessionScope.email}';
      var application = new Object();
      application.adminEmail = '${sessionScope.email}';
      var service = new Object();
      service.adminEmail = '${sessionScope.email}';
      var workflow = new Object();
      workflow.adminEmail = '${sessionScope.email}';
      integration.user.name = '${sessionScope.email}';
      integration.user.email = '${sessionScope.email}';
      integration.user.userId = '${sessionScope.userId}';
      admin.service.getAllServiceList(service, getAllServiceDataSuccess,
        getAllServiceDataFailure);
      admin.client.getAllClientsOfUser(client, getClientDataSuccess,
        getClientDataFailure);
      admin.application.getAllApplicationsOfUser(application,
        getApplicationDataSuccess, getApplicationDataFailure);
      admin.workflow.getAllWorkflowsOfUser(workflow, getWorkflowDataSuccess,
        getWorkflowDataFailure);
      // disableCallback(true);
    }
    // var disableCallback = function(isTrue) {
    //   if (isTrue) {
    //     $("#reg-app-callbackurl").prop('disabled', true);
    //     document.getElementById('reg-app-eventtype').value = "pull";
    //   } else {
    //     $("#reg-app-callbackurl").prop('disabled', false);
    //     document.getElementById('reg-app-eventtype').value = "push";
    //   }
    // }
  </script>
</head>

<body class="fullbleed layout vertical" onload="renderRegisteredUtils();">
  <c:choose>
    <c:when test="${not empty sessionScope.email}">
  <paper-toolbar class="tall">
    <paper-icon-button id="home_button" icon="extension"></paper-icon-button>
    <span title>Teamchat Integration Console</span>
    <h4><c:out value="${sessionScope.profileName}"></c:out></h4>
    <paper-icon-button id="logout_button" icon="exit-to-app">Logout</paper-icon-button>
    <paper-tabs id="select_tab" selected="0" class="bottom self-end" style="width: 600px;">
      <paper-tab>PLATFORM CLIENT</paper-tab>
      <paper-tab>SERVICES</paper-tab>
      <paper-tab>APPLICATIONS</paper-tab>
      <paper-tab>WORKFLOWS</paper-tab>
    </paper-tabs>
    <div class="bottom flex"></div>
  </paper-toolbar>
  <neon-animated-pages id="page" class="flex" selected="0" entry-animation="fade-in-animation" exit-animation="fade-out-animation">
    <neon-animatable>
      <div class="content">
        <h3>
          Register Client
        </h3>
        <p>
          This section is used to create a client (customer Id) on Teamchat Integration platform , later you can use this client id to create / consume / register application / services / workflows . This will also help you maintain and manage the application ,services
          , workflows you create
        </p>
      </div>
      <table id="registeredClientsTable">
      </table>
      <paper-fab icon="add" id="register_client_button" title="Register a new client"></paper-fab>
      <form onsubmit="return false" method="post">
        <paper-dialog style="max-height:460px;min-width:350px;" id="register_client" entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
          <h2>Register a new Client</h2>
          <div>
            <paper-input name="reg-client-name" id="reg-client-name" required auto-validate label="Name" autofocus char-counter maxlength="128"></paper-input>
            <paper-input name="reg-client-org" id="reg-client-org" required auto-validate label="Organization" char-counter maxlength="128"></paper-input>
            <gold-email-input name="reg-client-email" id="reg-client-email" required auto-validate error-message="Please enter a valid email" label="Email"></gold-email-input>
            <paper-textarea name="reg-client-description" id="reg-client-description" label="Description" char-counter maxlength="256"></paper-textarea>
          </div>
          <!-- </paper-dialog-scrollable> -->
          <div class="buttons">
            <paper-button class="discard" dialog-dismiss raised>Discard</paper-button>
            <paper-button id="reg-client-save" onclick="registerClient()" class="submit-button" dialog-confirm raised>Create</paper-button>
          </div>
        </paper-dialog>
      </form>
    </neon-animatable>
    <neon-animatable>
      <div class="content">
        <h3>
          Self Serve Integration Services
        </h3>
        <p>
          This section id used to create a client (customer Id) on Teamchat Integration platfrom , later you can use this client id to create / consume / register application ,services , workflows . This will also help you maintain and manage the application ,services
          , workflows you create
        </p>
        <div id="availableServices" class="services">
        </div>
      </div>
      <iron-collapse id="service-loader">
        <paper-spinner active></paper-spinner>
      </iron-collapse>
      <iron-collapse id="service-main">
        <div class="content">
          <h3>
            <img src="img/git.png" alt="github" />
            <span>Git</span>
            <paper-button id="register_this_button" onclick="addService()" raised>Create New</paper-button>
            <paper-button id="register_close_button" class="discard" raised>Close</paper-button>
          </h3>
          <p>
            This section is used to create a client (customer Id) on Teamchat Integration platfrom. Later you can use this client id to create / consume / register -
            <ol>
              <li>Applications</li>
              <li>Services</li>
              <li>Workflows</li>
            </ol> This will also help you maintain and manage the applications ,services and workflows that you create
          </p>
        </div>
        <table id="registeredServicesTable">
        </table>
      </iron-collapse>
      <form onsubmit="return false" id="service-form" method="post">
        <paper-dialog id="register_service" with-backdrop entry-animation="scale-up-animation" exit-animation="fade-out-animation" with-backdrop>
          <h2>Register a new Service</h2>
          <paper-dialog-scrollable>
            <div id="clientDrop" class="list drop-down">
              <div class="flex">Select Client</div>
              <iron-icon icon="arrow-drop-down"></iron-icon>
            </div>
            <div id="clientDropBox" class="horizontal-section drop-down-data">
              <paper-menu class="list innerlist">
              </paper-menu>
            </div>
            <input type="hidden" name="reg-service-cKey" id="reg-service-cKey" value="">
            <paper-input name="reg-service-name" id="reg-service-name" required auto-validate label="Name" char-counter maxlength="128"></paper-input>
            <paper-textarea name="reg-service-desc" id="reg-service-desc" label="Description" char-counter maxlength="256"></paper-textarea>
          </paper-dialog-scrollable>
          <div class="buttons">
            <paper-button class="discard" dialog-dismiss raised>Discard</paper-button>
            <paper-button class="submit-button" onclick="registerService()" dialog-confirm autofocus raised>Create</paper-button>
          </div>
        </paper-dialog>
      </form>
    </neon-animatable>
    <neon-animatable>
      applications
    </neon-animatable>
    <neon-animatable>
      workflows
    </neon-animatable>
  </neon-animated-pages>
  <paper-toast id="toaster" text="An Error Occured"></paper-toast>
  <script src="scripts/windowend.js"></script>
  </c:when>
  <c:otherwise>
    <c:redirect url="/login.html"></c:redirect>
  </c:otherwise>
</c:choose>
</body>

</html>
