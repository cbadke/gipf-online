// Generated by CoffeeScript 1.3.3
(function() {

  $(function() {
    return ($('.nav-link')).hover((function() {
      return ($(this)).animate({
        'margin-left': '+=25px'
      }, 200, 'swing');
    }), (function() {
      return ($(this)).animate({
        'margin-left': '-=25px'
      }, 200, 'swing');
    }));
  });

}).call(this);
