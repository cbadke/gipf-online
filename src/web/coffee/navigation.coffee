$ ->
  ($ '.nav-link').hover (
    -> ($ @).animate {'margin-left': '+=25px'}, 200, 'swing'), (
    -> ($ @).animate {'margin-left': '-=25px'}, 200, 'swing')
