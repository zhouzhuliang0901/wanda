Array.prototype.removeArr = function (arr) {
    for (var i = 0; i < arr.length; i++) {
        if (this.indexOf(arr[i]) !== -1) {
            for (var j = 0; j < this.length; j++) {
                if (this.indexOf(arr[i]) !== -1) {
                    var t = this.splice(this.indexOf(arr[i]), 1);
                    --j;
                }
            }
        }
    }
    return this;
};
