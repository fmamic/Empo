module.exports = {
    build_dir: 'build',
    compile_dir: 'bin',
    app_files: {
        js: ['src/**/*.js', '!src/**/*.spec.js', '!src/assets/**/*.js'],
        jsunit: ['src/**/*.spec.js'],

        coffee: ['src/**/*.coffee', '!src/**/*.spec.coffee'],
        coffeeunit: ['src/**/*.spec.coffee'],

        atpl: ['src/app/**/*.tpl.html'],
        ctpl: ['src/common/**/*.tpl.html'],

        html: ['src/index.html'],
        less: 'src/less/main.less'
    },
    test_files: {
        js: [
            'vendor/angular-mocks/angular-mocks.js'
        ]
    },
    vendor_files: {
        js: [
            'vendor/jquery/dist/jquery.min.js',
            'vendor/jquery/dist/jquery.min.map',
            'vendor/selectize/dist/js/standalone/selectize.min.js',
            'vendor/angular/angular.js',
            'vendor/angular-bootstrap/ui-bootstrap.min.js',
            'vendor/angular-bootstrap/ui-bootstrap-tpls.min.js',
            'vendor/placeholders/angular-placeholders-0.0.1-SNAPSHOT.min.js',
            'vendor/angular-ui-router/release/angular-ui-router.js',
            'vendor/angular-ui-utils/modules/route/route.js',
            'vendor/Chart.js/Chart.min.js',
            'vendor/angular-chart.js/dist/angular-chart.min.js',
            'vendor/angular-chart.js/dist/angular-chart.min.js.map',
            'vendor/angular-file-upload/angular-file-upload.min.js',
            'vendor/angular-file-upload/angular-file-upload.min.map',
            'vendor/es5-shim/es5-sham.min.js',
            'vendor/es5-shim/es5-sham.map',
            'vendor/es5-shim/es5-shim.min.js',
            'vendor/es5-shim/es5-shim.map',
            'vendor/ngInfiniteScroll/build/ng-infinite-scroll.min.js',
            'vendor/chronoline/raphael-min.js',
            'vendor/qtip2/jquery.qtip.min.js',
            'vendor/qtip2/jquery.qtip.min.map',
            'vendor/qtip2/jquery.qtip.min.js.map',
            'vendor/chronoline/chronoline.js',
            'vendor/bootstrap/dist/js/bootstrap.min.js',
            'vendor/microplugin/src/microplugin.js',
            'vendor/sifter/sifter.min.js',
            'vendor/angular-selectize2/dist/selectize.js'
        ],
        css: [
            'vendor/selectize/dist/css/selectize.default.css',
            'vendor/angular-chart.js/dist/angular-chart.css',
            'vendor/angular-chart.js/dist/angular-chart.css.map',
            'vendor/chronoline/chronoline.css',
            'vendor/qtip2/jquery.qtip.min.css',
            'vendor/bootstrap/dist/css/bootstrap.min.css',
            'vendor/bootstrap/dist/css/bootstrap.css.map'
        ],
        assets: [
            'vendor/angular-chart.js/dist/angular-chart.css.map',
            'vendor/bootstrap/assets/glyphicons-halflings-regular.eot',
            'vendor/bootstrap/assets/glyphicons-halflings-regular.svg',
            'vendor/bootstrap/assets/glyphicons-halflings-regular.ttf',
            'vendor/bootstrap/assets/glyphicons-halflings-regular.woff'
        ]
    }
};
