var profile = angular.module('ngBoilerplate.profile', [
    'selectize', 'ui.router', 'chart.js', 'ui.bootstrap'
])
    .config(function config($stateProvider) {
        $stateProvider.state('profile', {
            url: '/profile',
            views: {
                "main": {
                    controller: 'ProfileCtrl',
                    templateUrl: 'profile/profile.tpl.html'
                }
            },
            data: {pageTitle: 'Profile'}
        }).state('profile.detail', {
            url: '/:id',
            views: {
                "main": {
                    controller: 'ProfileCtrl',
                    templateUrl: 'profile/profile.tpl.html'
                }
            },
            data: {pageTitle: 'Profile'}
        });
    })
    .service('personService', function () {
        this.person = {};
    })
    .controller('ProfileCtrl', function ProfileController($scope, $http, $location, $modal, $state, $stateParams, personService) {
        $scope.personService = personService;

        if ($stateParams.id != null) {
            $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/user/' + $stateParams.id).
                success(function (data) {
                    $scope.personService.person = data;
                }).
                error(function (data, status, headers, config) {
                });
        }

        $scope.location = $location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/build/assets/';

        $scope.openTag = function (id) {
            $state.go('tag');
        };
    }).controller('PopoverDemoCtrl', function ($scope) {
        $scope.dynamicPopover = {
            content: 'Hello, World!',
            templateUrl: 'myPopoverTemplate.html',
            title: 'Title'
        };
    }).controller("RadarCtrl", function ($scope) {
        $scope.labels = ["Tagovi", "Iskustvo", "Badževi", "Projekti", "Prosječni level"];

        $scope.data = [
            [12, 25, 3, 10, 19]
        ];
    }).controller('AddTagModalCtrl', function ($scope, $modal) {
        $scope.animation = true;
        $scope.open = function () {
            $modal.open({
                animation: $scope.animation,
                templateUrl: 'profile/addTag.tpl.html',
                controller: 'AddTagModalInstanceCtrl'
            });
        };

        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animation;
        };
    }).controller('AddTagModalInstanceCtrl', function ($scope, $modalInstance, $http, $location) {
        $scope.form = {};

        $scope.submitForm = function (valid) {
            if (valid) {
                var tag = {
                    name: $scope.form.name,
                    displayName: $scope.form.displayName,
                    description: $scope.form.description,
                    tagTypeId: $scope.form.tagType,
                    link: $scope.form.link
                };

                $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/save/tag', tag).
                    success(function (data) {
                        if (data) {

                        }
                    }).
                    error(function (data, status, headers, config) {
                    });

                $modalInstance.close(true);
            }
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

    }).controller('AddBadgeModalCtrl', function ($scope, $modal) {

        $scope.animationsEnabled = true;
        $scope.open = function () {

            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'profile/addBadge.tpl.html',
                controller: 'AddBadgeModalInstanceCtrl'
            });

            modalInstance.result.then(function (selectedItem) {
            }, function () {
            });
        };

        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
        };

    }).controller('AddBadgeModalInstanceCtrl', function ($scope, $http, $location, $modalInstance, $filter) {
        $scope.form = {};
        $scope.saveBadge = function (valid) {
            if (valid) {
                var badge = {
                    name: $scope.form.name,
                    displayName: $scope.form.displayName,
                    tagId: $scope.form.tag,
                    badgeTypeId: $scope.form.badgeType,
                    level: $scope.form.level,
                    description: $scope.form.description,
                    date: $filter('date')(new Date(), 'yyyy-MM-dd'),
                    userId: 4 // TODO current user
                };

                $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/save/badge', badge).
                    success(function (data) {
                        if (data) {

                        }
                    }).
                    error(function (data, status, headers, config) {
                    });

                $modalInstance.close(true);
            }
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }).controller('AddProjectModalCtrl', function ($scope, $modal) {

        $scope.animationsEnabled = true;
        $scope.open = function () {

            var modalInstance = $modal.open({
                animation: $scope.animationsEnabled,
                templateUrl: 'profile/addProject.tpl.html',
                controller: 'AddProjectModalInstanceCtrl'
            });

            modalInstance.result.then(function (selectedItem) {
            }, function () {
            });
        };

        $scope.toggleAnimation = function () {
            $scope.animationsEnabled = !$scope.animationsEnabled;
        };

    }).controller('AddProjectModalInstanceCtrl', function ($scope, $modalInstance) {
        $scope.ok = function () {
            $modalInstance.close($scope.selected.item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    });

profile.controller('EndDateCtrl', function ($scope) {
    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.disabled = function (date, mode) {
        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function () {
        $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.format = 'dd.MM.yyyy';

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);

    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 2);

    $scope.events =
        [
            {
                date: tomorrow,
                status: 'full'
            },
            {
                date: afterTomorrow,
                status: 'partially'
            }
        ];
});

profile.controller('StartDateCtrl', function ($scope) {
    $scope.today = function () {
        $scope.dt = new Date();
    };
    $scope.today();

    $scope.clear = function () {
        $scope.dt = null;
    };

    $scope.disabled = function (date, mode) {
        return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
    };

    $scope.toggleMin = function () {
        $scope.minDate = $scope.minDate ? null : new Date();
    };
    $scope.toggleMin();

    $scope.open = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();

        $scope.opened = true;
    };

    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };

    $scope.format = 'dd.MM.yyyy';

    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);

    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 2);

    $scope.events =
        [
            {
                date: tomorrow,
                status: 'full'
            },
            {
                date: afterTomorrow,
                status: 'partially'
            }
        ];
});

profile.controller('SelectizeCtrl', function ($scope, $location, $http) {
    $scope.tagTypes = [];

    $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/tagtype/all').
        success(function (data) {
            angular.forEach(data, function (value) {
                $scope.tagTypes.push({value: value.id, text: value.name});
            });
        }).
        error(function (data, status, headers, config) {
        });

    $scope.singleConfig = {
        create: false,
        sortField: 'text',
        maxItems: 1
    };
});

profile.controller('TimelineBadgeCtrl', function ($scope, personService) {
    $scope.events = [];
    $scope.$watch('personService.person', function (person) {
        var personBadges = person.badgeForm;

        if (personBadges != null) {
            $scope.events = [];
            $.each(personBadges, function (index, value) {
                $scope.events.push({dates: [new Date(value.date)], title: value.name, description: value.description});
            });

            $("#timelineBadge").empty();

            new Chronoline(document.getElementById("timelineBadge"), $scope.events,
                {
                    animated: true,
                    tooltips: true,
                    visibleSpan: DAY_IN_MILLISECONDS * 150,
                    labelInterval: isHalfMonth,
                    hashInterval: isHalfMonth,
                    scrollLeft: prevQuarter,
                    scrollRight: nextQuarter
                });
        }
    });
}).service('badgeTimelineService', function () {
    this.badges = [];
});

profile.controller('TimelineProjectCtrl', function ($scope, $timeout) {
    $scope.events = [
        {dates: [new Date(2015, 4, 29)], title: "Java - Level 4", description: "Descriptio of this nice badge"},
        {dates: [new Date(2015, 3, 2)], title: "Java - Level 3", description: "Descriptio of this nice badge"},
        {dates: [new Date(2015, 2, 9)], title: "Spring - Level 3", description: "Descriptio of this nice badge"},
        {dates: [new Date(2015, 1, 9)], title: "Spring - Level 1", description: "Descriptio of this nice badge"},
        {dates: [new Date(2015, 1, 9)], title: "Spring - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2015, 1, 11)], title: "Spring - Level 3", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 12, 9)], title: "Hibernate - Level 3", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 11, 9)], title: "Hibernate - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 10, 9)], title: "PL/SQL - Level 3", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 9, 9)], title: "PL/SQL - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 8, 9)], title: "PL/SQL - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 7, 9)], title: "PL/SQL - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2014, 6, 9)], title: "PL/SQL - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2013, 6, 9)], title: "PL/SQL - Level 2", description: "Descriptio of this nice badge"},
        {dates: [new Date(2013, 4, 9)], title: "PL/SQL - Level 2", description: "Descriptio of this nice badge"}
    ];

    $timeout(function () {
        new Chronoline(document.getElementById("timelineProject"), $scope.events,
            {
                animated: true,
                tooltips: true,
                visibleSpan: DAY_IN_MILLISECONDS * 150,
                labelInterval: isHalfMonth,
                hashInterval: isHalfMonth,
                scrollLeft: prevQuarter,
                scrollRight: nextQuarter
            });
    });
});

profile.service('selectedTagService', function () {
    this.selectedItems = [];
}).service('selectedBadgeService', function () {
    this.selectedItems = [];
});

profile.controller('ExistingBadgeCtrl', function ($scope, $modal) {
    $scope.animation = true;

    $scope.open = function () {
        $modal.open({
            animation: $scope.animation,
            templateUrl: 'profile/existingBadge.tpl.html',
            controller: 'ExistingBadgeModalInstanceCtrl'
        });
    };

    $scope.toggleAnimation = function () {
        $scope.animationsEnabled = !$scope.animation;
    };
}).controller('ExistingBadgeModalInstanceCtrl', function ($scope, $http, $location, $modalInstance, $state, selectedBadgeService, personService) {
    $scope.personId = $state.params.id;
    $scope.service = selectedBadgeService;
    $scope.personService = personService;

    $scope.ok = function () {
        var userBadges = {
            personId: $scope.personId,
            badgesId: $scope.service.selectedItems
        };

        $scope.service.selectedItems = [];

        $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/save/user/badges', userBadges).
            success(function (data) {
                $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/user/' + $state.params.id).
                    success(function (data) {
                        $scope.personService.person = data;
                        $modalInstance.close(true);
                    }).
                    error(function (data, status) {
                        $modalInstance.close(status);
                    });
            }).
            error(function (data, status) {
                $modalInstance.close(status);
            });
    };

    $scope.cancel = function () {
        $modalInstance.close(true);
    };
});

profile.controller('ExistingTagCtrl', function ($scope, $modal) {
    $scope.animation = true;

    $scope.open = function () {
        $modal.open({
            animation: $scope.animation,
            templateUrl: 'profile/existingTag.tpl.html',
            controller: 'ExistingTagModalInstanceCtrl'
        });
    };

    $scope.toggleAnimation = function () {
        $scope.animationsEnabled = !$scope.animation;
    };
}).controller('ExistingTagModalInstanceCtrl', function ($scope, $http, $location, $modalInstance, $state, $rootScope, selectedTagService, personService) {
    $scope.personId = $state.params.id;
    $scope.service = selectedTagService;
    $scope.personService = personService;

    $scope.ok = function () {
        var userTags = {
            personId: $scope.personId,
            tagsId: $scope.service.selectedItems
        };

        $scope.service.selectedItems = [];

        $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/save/user/tags', userTags).
            success(function (data) {
                $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/user/' + $state.params.id).
                    success(function (data) {
                        $scope.personService.person = data;
                        $modalInstance.close(true);
                    }).
                    error(function (data, status) {
                        $modalInstance.close(status);
                    });
            }).
            error(function (data, status) {
                $modalInstance.close(status);
            });
    };

    $scope.cancel = function () {
        $modalInstance.close(true);
    };
}).controller('SelectizeTagsCtrl', function ($scope, $http, $location, selectedTagService) {
    $scope.tags = [];

    $scope.selectedTagService = selectedTagService;

    $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/tags/all').
        success(function (data) {
            $scope.tags = [];
            angular.forEach(data, function (value) {
                $scope.tags.push({value: value.id, text: value.name});
            });
        }).
        error(function (data, status, headers, config) {
        });

    $scope.multipleConfig = {
        create: false,
        sortField: 'text'
    };

    $scope.singleConfig = {
        create: false,
        sortField: 'text',
        maxItems: 1
    };
}).controller('SelectizeBadgesCtrl', function ($scope, $http, $location, selectedBadgeService) {
    $scope.badgesList = [];

    $scope.selectedBadgeService = selectedBadgeService;

    $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/badges/all').
        success(function (data) {
            $scope.badgesList = [];
            angular.forEach(data, function (value) {
                $scope.badgesList.push({value: value.id, text: value.name});
            });
        }).
        error(function (data, status, headers, config) {
        });

    $scope.multipleConfig = {
        create: false,
        sortField: 'text'
    };

    $scope.singleConfig = {
        create: false,
        sortField: 'text',
        maxItems: 1
    };
}).controller('SelectBadgeTypeCtrl', function ($scope, $http, $location) {
    $scope.badgeTypes = [];

    $http.post($location.$$protocol + '://' + $location.$$host + ':' + $location.$$port + '/search/badgetype/all').
        success(function (data) {
            angular.forEach(data, function (value) {
                $scope.badgeTypes.push({value: value.id, text: value.name});
            });
        }).
        error(function (data, status, headers, config) {
        });

    $scope.singleConfig = {
        create: false,
        sortField: 'text',
        maxItems: 1
    };
}).controller('BadgeRatingCtrl', function ($scope) {
    $scope.rateStar = 7;
    $scope.max = 10;
    $scope.isReadonly = false;

    $scope.hoveringOver = function (value) {
        $scope.overStar = value;
        $scope.percent = 100 * (value / $scope.max);
    };
}).controller('CarouselBadgeCtrl', function ($scope, personService) {
    $scope.slides = [];

    $scope.$watch('personService.person', function (person) {
        var personBadges = person.badgeForm;

        if (personBadges != null) {
            $scope.slides = [];
            $.each(personBadges, function (index) {
                if (index % 3 === 0) {
                    $scope.badges = [];
                    $scope.badges.push({name: personBadges[index].name, image: 'cert', level: personBadges[index].level, date: personBadges[index].date, description: personBadges[index].description});

                    if (index + 1 < personBadges.length) {
                        $scope.badges.push({
                            name: personBadges[index + 1].name,
                            image: 'badge1',
                            level: personBadges[index + 1].level,
                            date: personBadges[index + 1].date,
                            description: personBadges[index + 1].description
                        });
                    }

                    if (index + 2 < personBadges.length) {
                        $scope.badges.push({
                            name: personBadges[index + 2].name,
                            image: 'badge2',
                            level: personBadges[index + 2].level,
                            date: personBadges[index + 2].date,
                            description: personBadges[index + 2].description
                        });
                    }
                    $scope.slides.push({badges: $scope.badges});
                }
            });
        }
    }, true);

}).controller('CarouselProjectCtrl', function ($scope) {
    $scope.slides = [
        {
            projects: [
                {
                    title: "Porezna uprava",
                    description: "Modeliranje i arhitektura podataka."
                },
                {
                    title: "RBA public portal",
                    description: "Public web portal za Raiffeisen banku."
                },
                {
                    title: "Vipnet RMS",
                    description: "Vipnet sustav za registracijus mobilnih uređaja."
                }
            ]
        },
        {
            projects: [
                {
                    title: "CrossIdeas integracija",
                    description: "Integracija CorssIdeasa i IBM ITIM."
                },
                {
                    title: "AKD ERV",
                    description: "Evidencija radnog vremena AKD"
                },
                {
                    title: "MORH EVRA",
                    description: "Evidencija računala i računalnih sustava."
                }
            ]
        }];
});
