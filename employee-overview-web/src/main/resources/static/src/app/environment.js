angular.module('taggy')
    .constant('AUTH', {
        'TASHI_URL': 'http://localhost:8080/api/oauth/authorize?',
        'CLIENT_ID': 'taggy-web-ui',
        'REDIRECT_URL': 'http%3A%2F%2Flocalhost%3A9090%2Fbuild%2Findex.html%23%2Fhome',
        'SCOPE': 'read+write',
        'RESPONSE': 'token'
    }).constant('USER_ENV', {
        'TASHI_USERS_URL': 'http://localhost:8080/api/users'
    });
