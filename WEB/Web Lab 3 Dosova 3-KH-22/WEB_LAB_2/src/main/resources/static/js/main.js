var JWT = "";

// Компонент для авторизації
Vue.component('auth-bar', {
    template: `
        <div>
            <div class="field">
                <label class="label">Username</label>
                <div class="control">
                    <input type="login" class="input" v-model="login" />
                </div>
            </div>
            <div>
                <label class="label">Password</label>
                <div class="field has-addons">
                    <div class="control is-expanded">
                        <input type="password" class="input" v-model="password" />
                    </div>
                </div>
            </div>
            <input type="button" class="button" value="Login" @click="auth" />
            <div v-if="token" class="token-display">
                <strong>JWT:</strong> {{ token }}
            </div>
        </div>`,
    methods: {
        auth: function () {
            this.$http
                .get('auth/authorisation?login=' + this.login + '&password=' + this.password)
                .then((response) => {
                    JWT = response.bodyText;
                    this.token = JWT;
                });
        },
    },
    data() {
        return {
            login: '',
            password: '',
            token: '',
        };
    },
});

// Компонент для завантаження даних
Vue.component('persons-info-bar', {
    props: ['persons'],
    template: `
        <div>
            <input type="button" value="Load Data" @click="load" />
            <div v-for="person in persons" :key="person.id">
                <div>
                    <i>({{ person.id }})</i>
                    {{ person.lastName }} {{ person.firstName }}
                    <input type="button" value="X" @click="del"/>
                </div>
            </div>
        </div>`,
    methods: {
        load: function () {
            if (!JWT) {
                alert("Please log in to access data.");
                return;
            }

            const headers = {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + JWT,
            };Vue.component('persons-info-bar', {
                  props: ['persons'],
                  template: `
                      <div>
                          <input type="button" value="Load Data" @click="load" />
                          <div v-for="person in persons" :key="person.id">
                              <div>
                                  <i>({{ person.id }})</i>
                                  {{ person.lastName }} {{ person.firstName }}
                                  <input type="button" value="X" @click="del(person)" />
                              </div>
                          </div>
                      </div>`,
                  methods: {
                      load: function () {
                          if (!JWT) {
                              alert("Please log in to access data.");
                              return;
                          }

                          const headers = {
                              'Content-Type': 'application/json',
                              'Authorization': 'Bearer ' + JWT,
                          };

                          this.$http
                              .get('http://localhost:45000/classes/persons', { headers })
                              .then((response) => {
                                  response.json().then((data) => {
                                      this.persons.splice(0);
                                      data.forEach((person) => this.persons.push(person));
                                  });
                              })
                              .catch((error) => {
                                  // Обробка помилок
                                  if (error.status === 401) {
                                      alert('Помилка завантаження даних,\nспочатку треба авторизуватись');
                                  } else if (error.status === 500) {
                                      alert('Помилка на сервері. Спробуйте пізніше.');
                                  } else {
                                      alert('Невідома помилка,\nповторіть пізніше');
                                  }
                              });
                      },

                      del: function(person) {
                          if (!JWT) {
                              alert("Please log in to perform this action.");
                              return;
                          }

                          const headers = {
                              'Content-Type': 'application/json',
                              'Authorization': 'Bearer ' + JWT,
                          };

                          this.$http
                              .delete('http://localhost:45000/classes/persons/' + person.id, { headers })
                              .then(response => {
                                  this.persons.splice(this.persons.indexOf(person), 1);
                                  alert('Запис успішно видалено');
                              })
                              .catch(error => {
                                  if (error.status === 401) {
                                      alert('Помилка видалення даних,\nспочатку треба авторизуватись');
                                  } else if (error.status === 403) {
                                      alert('Для видалення недостатньо прав');
                                  } else if (error.status === 401 && error.bodyText.includes("expired")) {
                                      alert('Термін дії вашого токену сплив. Потрібно повторно авторизуватись');
                                  } else {
                                      alert('Невідома помилка,\nповторіть пізніше');
                                  }
                              });
                      },
                  },
              });


// Створення додатку Vue
new Vue({
    el: '#app',
    template: `
        <div>
            <auth-bar />
            <persons-info-bar :persons="persons" />
        </div>`,
    data: {
        persons: [],
    },
});
