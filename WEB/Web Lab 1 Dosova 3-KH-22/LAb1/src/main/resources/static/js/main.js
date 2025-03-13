Vue.component('auth-bar', {
    template:
      '<div>'+
          '<div class="field">'+
              '<label class="label">Username</label>'+
              '<div class="control">'+
                  '<input type="text" class="input" v-model="login"/>'+
              '</div>'+
          '</div>'+
          '<div>'+
              '<label class="label">Password</label>'+
              '<div class="control">'+
                  '<input type="password" class="input" v-model="password"/>'+
              '</div>'+
          '</div>'+
          '<input type="button" value="Login" @click="authorize"/>'+
          '<p>JWT: {{ JWT }}</p>'+
      '</div>',
    methods: {
        authorize() {
            this.$http.get('auth/authorisation?login=' + this.login + '&password=' + this.password)
            .then(response => {
                console.log('body: ' + response.bodyText);
                this.JWT = response.bodyText;
            });
        }
    },
    data() {
        return {
            login: '',
            password: '',
            JWT: ''
        };
    }
});

var app = new Vue({
    el: '#app',
    template: '<auth-bar />'
});
