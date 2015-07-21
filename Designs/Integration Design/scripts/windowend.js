//home button event
document.getElementById('home_button').onclick = function() {
  window.location = "dashboard.jsp";
};
//logout button event
document.getElementById('logout_button').onclick = function() {
  window.location = "login?operation=logout";
};
//click event of tab switching
document.getElementById('select_tab').onclick = function() {
  document.getElementById('page').selected = this.selected;
};
//click event of register a new client
document.getElementById('register_client_button').onclick = function() {
  var dialog = document.getElementById('register_client');
  if (dialog) {
    dialog.open();
  }
};
//open dialog for registration
document.getElementById('register_this_button').onclick = function() {
  var clientKey = document.getElementById('reg-service-cKey').value;
  //if no client is registered
  if (clientKey == null || clientKey == "" || clientKey == undefined) {
    showToast('toaster', 'Please register a client first');
  } else {
    var dialog = document.getElementById('register_service');
    if (dialog) {
      dialog.open();
    }
  }
};
//close dialog for registration
document.getElementById('register_close_button').onclick = function() {
  findParentById(this, "IRON-COLLAPSE").toggle();
};

//open drop down and close it
document.getElementById('clientDrop').onclick = function(event) {
  document.getElementById('clientDropBox').classList.toggle('open');
};
//open a particular service
// var elements = document.querySelectorAll('.service');
// Array.prototype.forEach.call(elements, function(el, i) {
//   el.addEventListener("click", function() {
//     //remove class hover
//     this.parentNode.classList.remove('open');
//     //check if a service is open or not
//     if (document.getElementById('service-main').opened) {
//       //close the existing service
//       document.getElementById('service-main').toggle();
//     }
//     //toggle open the spinner
//     document.getElementById('service-loader').toggle();
//     //set properties inside collapse
//     var strigger = this.classList[1].replace("-service", "");
//     setTimeout(function() {
//       //change text description of service
//       document.querySelector('#service-main .content h3 span').innerHTML = strigger.capitalize();
//       //change service icon
//       document.querySelector('#service-main .content h3 img').setAttribute('src', el.querySelector('img').getAttribute('src'));
//       //toggle open the spinner
//       document.getElementById('service-loader').toggle();
//       //toggle iron collapse
//       document.getElementById('service-main').toggle();
//     }, 1500);
//   });
// });

//submit button event
// var submitbuttons = document.querySelectorAll('.submit-button');
// Array.prototype.forEach.call(submitbuttons, function(el, i) {
//   el.addEventListener("click", function() {
//     //find parent form and submit it's data
//     findParentById(el, "FORM").submit();
//   });
// });
